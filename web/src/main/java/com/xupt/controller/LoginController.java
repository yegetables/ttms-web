package com.xupt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xupt.common.ResponseCode;
import com.xupt.common.ServerResponse;
import com.xupt.pojo.Users;
import com.xupt.service.UsersService;
import com.xupt.utils.CodeUtils;
import com.xupt.utils.MailTools;
import com.xupt.utils.PHPass;
import com.xupt.utils.RandomUtils;
import com.xupt.utils.RedisUtils;
import com.xupt.utils.TokenUtils;
import com.xupt.utils.UsersUtils;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
@ResponseBody
public class LoginController {
  @Resource private RedisUtils redisUtils;
  @Resource private MailTools mailTools;
  @Resource private PHPass phPass;
  @Resource private CodeUtils codeUtils;
  @Resource private UsersService usersService;

  /** 处理没有权限 */
  @RequestMapping("/login403")
  public ServerResponse<Boolean> login403() {
    return ServerResponse.createByErrorMsg("token错误，请重新登陆");
  }

  /**
   * 手机号或者邮箱+密码登录
   *
   * @param map (email/phoneNum) +密码
   * @param response 响应
   * @return 登录状态
   */
  @PostMapping("/login/byPassword")
  public ServerResponse<Users> login(
      @RequestBody Map<String, String> map, HttpServletResponse response) {
    String password = map.get("password");
    if (StringUtils.isBlank(password)) {
      log.info("[login/Password]" + "密码为空");
      return ServerResponse.createByErrorMsg("密码不能为空");
    }
    String phoneNum = map.get("phoneNum");
    String email = map.get("email");
    if (!StringUtils.isBlank(email) && !StringUtils.isBlank(phoneNum)) {
      log.info("[login/Password]" + "email和phoneNum不能同时存在");
      return ServerResponse.createByErrorMsg("email和phoneNum不能同时存在");
    }
    var p = checkoutUserExist(map);
    if (p.getStatus() != ResponseCode.SUCCESS.getCode() || p.getData() == null) {
      //  若不存在，不能登陆
      log.warn("[login/Password]" + "用户不存在，不能登陆");
      return p;
    }
    Users user = p.getData();
    if (!PHPass.CheckPassword(password, user.getPassword())) {
      log.warn("[login/Password]userId=" + user.getUid() + "密码错误");
      return ServerResponse.createByErrorMsg("密码错误");
    }
    var uid = user.getUid();
    String token;
    String oldToken = redisUtils.get(String.valueOf(uid));
    if (!StringUtils.isBlank(oldToken)) {
      token = oldToken;
    } else {
      token = TokenUtils.getToken(uid);
      redisUtils.set(String.valueOf(uid), token, 14 * 24 * 60 * 60);
    }
    response.addHeader("Access-Control-Expose-Headers", "token");
    response.addHeader("token", token);
    log.info("[login/Password]" + user.getUid() + "登录成功");
    return ServerResponse.createBySuccessMsgData("登录成功", UsersUtils.removeSecret(user));
  }

  /*
   * * 手机验证码发送
   */
  @PostMapping("/login/codePhoneSend")
  public ServerResponse<String> codePhoneSend(@RequestBody Map<String, String> map) {
    String phoneNum = map.get("phoneNum");
    if (StringUtils.isBlank(phoneNum)) {
      log.info("[login/codePhoneSend]" + "phoneNum为空");
      return ServerResponse.createByErrorMsg("手机号不能为空");
    }
    String code = RandomUtils.randomCode();
    //    var p = checkoutUserExist(map);
    //    if (p.getStatus() != ResponseCode.SUCCESS.getCode()) {
    //      return ServerResponse.createByErrorMsg("该用户不存在");
    //    }
    log.info("[login/codePhoneSend]" + phoneNum + "发送验证码" + code);
    codeUtils.SendCode(phoneNum, code);
    redisUtils.set(phoneNum + "code", code, 600);
    //    System.out.println("code:" + redisUtils.get(phoneNum + "code"));
    return ServerResponse.createBySuccessMsg("发送成功");
  }

  /*
   * * 邮箱验证码发送
   */
  @PostMapping("/login/codeEmailSend")
  public ServerResponse<String> codeEmailSend(@RequestBody Map<String, String> map) {
    String email = map.get("email");
    String code = RandomUtils.randomCode();
    if (StringUtils.isBlank(email)) {
      log.info("[login/codeEmailSend]" + "email为空");
      return ServerResponse.createByErrorMsg("邮箱不能为空");
    }
    //    var p = checkoutUserExist(map);
    //    if (p.getStatus() != ResponseCode.SUCCESS.getCode()) {
    //      return ServerResponse.createByErrorMsg(p.getMsg());
    //    }
    log.info("[login/codeEmailSend]" + email + "发送验证码" + code);
    mailTools.sendSimpleMail(email, code);
    redisUtils.set(email + "code", code, 600);
    //    System.out.println("code:" + redisUtils.get(email + "code"));
    return ServerResponse.createBySuccessMsg("发送成功");
  }

  /*
   * * 验证码登录
   */
  @PostMapping("/login/byCode")
  public ServerResponse<Users> loginByCode(
      @RequestBody Map<String, String> map, HttpServletResponse response) {
    var p = checkoutUserExist(map);
    if (p.getStatus() != ResponseCode.SUCCESS.getCode() || p.getData() == null) {
      // 不存在，无法验证码登陆
      log.warn("[login/byCode]" + "用户不存在，不能验证码登陆");
      return ServerResponse.createByErrorMsg("该用户不存在");
    }
    String code = map.get("code");
    if (StringUtils.isBlank(code)) {
      log.warn("[login/byCode]" + "输入验证码为空");
      return ServerResponse.createByErrorMsg("验证码不能为空");
    }
    code = code.replace("\"", "");

    String reallyCode = null;
    if (map.containsKey("email")) {
      String email = map.get("email");
      if (StringUtils.isBlank(email)) {
        log.warn("[login/byCode]" + "输入邮箱为空");
        return ServerResponse.createByErrorMsg("邮箱不能为空");
      }
      reallyCode = redisUtils.get(email + "code");
    } else if (map.containsKey("phoneNum")) {
      String phoneNum = map.get("phoneNum");
      if (StringUtils.isBlank(phoneNum)) {
        log.warn("[login/byCode]" + "输入手机号为空");
        return ServerResponse.createByErrorMsg("手机号不能为空");
      }
      reallyCode = redisUtils.get(phoneNum + "code");
    } else {
      log.warn("[login/byCode]" + "需要email或者phoneNum,,登陆失败");
      return ServerResponse.createByErrorMsg("需要email或者phoneNum,登陆失败");
    }

    if (StringUtils.isBlank(reallyCode)) {
      log.warn("[login/byCode]" + "验证码已过期");
      return ServerResponse.createByErrorMsg("验证码不存在/已过期");
    }
    if (!reallyCode.equals(code)) {
      log.info("[login/byCode]" + "验证码错误");
      return ServerResponse.createByErrorMsg("验证码错误");
    }
    Users user = p.getData();
    Integer uid = user.getUid();
    String token = TokenUtils.getToken(uid);
    //    log.info("[Success]token生成成功");
    // System.out.println(token);
    redisUtils.set(String.valueOf(uid), token, 14 * 24 * 60 * 60);
    response.addHeader("Access-Control-Expose-Headers", "token");
    response.addHeader("token", token);
    //    log.info("[Success]登录成功");
    return ServerResponse.createBySuccessMsgData("登录成功", UsersUtils.removeSecret(user));
  }

  /*
   * * 注册
   *  需要至少  password + username + email/phoneNum + code
   */
  @PostMapping("/register")
  public ServerResponse<String> register(@RequestBody Map<String, String> map) {
    String password = map.get("password");
    if (StringUtils.isBlank(password)) {
      log.warn("[register]" + "输入密码为空");
      return ServerResponse.createByErrorMsg("密码不能为空");
    }
    String username = map.get("username");
    if (StringUtils.isBlank(username)) {
      log.warn("[register]" + "输入用户名为空");
      return ServerResponse.createByErrorMsg("用户名不能为空");
    }
    String code = map.get("code");
    if (StringUtils.isBlank(code)) {
      log.warn("[register]" + "输入验证码为空");
      return ServerResponse.createByErrorMsg("验证码不能为空");
    }

    var p = checkoutUserExist(map);
    if (p.getStatus() == ResponseCode.SUCCESS.getCode() || p.getData() != null) {
      log.warn("[register]" + "用户已存在，不能注册");
      return ServerResponse.createByErrorMsg(p.getMsg());
    }
    String reallyCode;
    if (map.containsKey("phoneNum")) {
      String phoneNum = map.get("phoneNum");
      if (StringUtils.isBlank(phoneNum)) {
        return ServerResponse.createByErrorMsg("手机号不能为空");
      }
      reallyCode = redisUtils.get(phoneNum + "code");
    } else if (map.containsKey("email")) {
      String email = map.get("email");
      if (email == null) {
        return ServerResponse.createByErrorMsg("邮箱不能为空");
      }
      reallyCode = redisUtils.get(email + "code");
    } else {
      log.warn("[register]" + "需要email或者phoneNum,注册失败");
      return ServerResponse.createByErrorMsg("注册失败");
    }
    if (StringUtils.isBlank(reallyCode)) {
      log.warn("[register]" + "验证码已过期");
      return ServerResponse.createByErrorMsg("验证码不存在/已过期");
    }
    reallyCode = reallyCode.replace("\"", "");
    if (!reallyCode.equals(code)) {
      log.warn("[register]" + "验证码错误");
      return ServerResponse.createByErrorMsg("验证码错误");
    }
    Users newUsers = new Users();
    newUsers.setUsername(username);
    newUsers.setPassword(phPass.HashPassword(password));
    newUsers.setEmail(map.get("email"));
    newUsers.setPhoneNum(map.get("phoneNum"));
    if (map.containsKey("age")) {
      newUsers.setAge(Integer.parseInt(map.get("age")));
    }
    if (map.containsKey("gender")) {
      newUsers.setGender(map.get("gender"));
    }
    try {
      usersService.register(newUsers);
      log.info("[register]注册成功" + newUsers.toString());
      return ServerResponse.createBySuccessMsg("注册成功");
    } catch (Exception e) {
      e.printStackTrace();
      log.error("[register]注册失败" + e.getMessage());
      return ServerResponse.createByErrorMsg("注册失败");
    }
  }

  private ServerResponse<Users> checkHasEmailOrPhoneNum(Map<String, String> map) {
    //    System.out.println("map:" + map);
    String email = map.get("email");
    String phoneNum = map.get("phoneNum");
    if (email == null && phoneNum == null) {
      return ServerResponse.createByErrorMsg("邮箱或手机号不能为空");
    }
    //    if (email != null && phoneNum != null) {
    //      return ServerResponse.createByErrorMsg("只能使用邮箱或手机号");
    //    }
    Users u = new Users();
    if (email != null) {
      u.setEmail(email);
    }
    if (phoneNum != null) {
      u.setPhoneNum(phoneNum);
    }
    return ServerResponse.createBySuccessMsgData("email/phone存在", u);
  }

  private ServerResponse<Users> checkoutUserExist(Users u) {
    if (u.getPhoneNum() != null) {
      Users user = usersService.getOne(new QueryWrapper<Users>().eq("phone_num", u.getPhoneNum()));
      if (user != null) {
        return ServerResponse.createBySuccessMsgData("手机号已存在", user);
      }
    }
    if (u.getEmail() != null) {
      Users user = usersService.getOne(new QueryWrapper<Users>().eq("email", u.getEmail()));
      if (user != null) {
        return ServerResponse.createBySuccessMsgData("邮箱已存在", user);
      }
    }
    return ServerResponse.createByErrorMsg("用户不存在");
  }

  /**
   * 检查用户是否存在
   *
   * @param map
   * @return
   */
  private ServerResponse<Users> checkoutUserExist(Map<String, String> map) {
    var p = checkHasEmailOrPhoneNum(map);
    if (p.getStatus() != ResponseCode.SUCCESS.getCode() || p.getData() == null) {
      return ServerResponse.createByErrorMsg(p.getMsg());
    }
    return checkoutUserExist(p.getData());
  }
}
