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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    return ServerResponse.createByErrorMsg("没有权限");
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
    System.out.println("map:" + map);
    String password = map.get("password");
    if (password == null) {
      return ServerResponse.createByErrorMsg("密码不能为空");
    }
    var p = checkoutUserExist(map);
    if (p.getStatus() != ResponseCode.SUCCESS.getCode()) {
      return p;
    }
    Users user = p.getData();
    if (!PHPass.CheckPassword(password, user.getPassword())) {
      //      log.warn("[Warn]该用户" + email + "密码错误");
      return ServerResponse.createByErrorMsg("密码错误");
    }
    var uid = String.valueOf(user.getUid());
    String token;
    String oldToken = redisUtils.get(uid);
    if (!Objects.isNull(oldToken)) {
      token = oldToken;
    } else {
      token = TokenUtils.getToken(uid);
      redisUtils.set(uid, token, 14 * 24 * 60 * 60);
    }
    response.addHeader("Access-Control-Expose-Headers", "token");
    response.addHeader("token", token);
    //    log.info("[Success]登录成功");
    return ServerResponse.createBySuccessMsgData("登录成功", UsersUtils.removeSecret(user));
  }

  /*
   * * 手机验证码发送
   */

  @PostMapping("/login/codePhoneSend")
  public ServerResponse<String> codePhoneSend(@RequestParam("phoneNum") String phoneNum) {
    String code = RandomUtils.randomCode();
    Map<String, String> map = new HashMap<>();
    map.put("phoneNum", phoneNum);
    var p = checkoutUserExist(map);
    //    if (p.getStatus() != ResponseCode.SUCCESS.getCode()) {
    //      return ServerResponse.createByErrorMsg("该用户不存在");
    //    }
    // TODO:phone code send

    //    String mailAddress = user.getEmail();
    //    mailTools.sendSimpleMail(mailAddress, code);
    codeUtils.SendCode(phoneNum);
    redisUtils.set(phoneNum + "code", code, 60);
    return ServerResponse.createBySuccessMsg("发送成功");
  }

  /*
   * * 邮箱验证码发送
   */
  @PostMapping("/login/codeEmailSend")
  public ServerResponse<String> codeEmailSend(@RequestParam("email") String email) {
    String code = RandomUtils.randomCode();
    Map<String, String> map = new HashMap<>();
    map.put("email", email);
    var p = checkoutUserExist(map);
    //    if (p.getStatus() != ResponseCode.SUCCESS.getCode()) {
    //      return ServerResponse.createByErrorMsg("该用户不存在");
    //    }
    mailTools.sendSimpleMail(email, code);
    redisUtils.set(email + "code", code, 60);
    return ServerResponse.createBySuccessMsg("发送成功");
  }

  /*
   * * 验证码登录
   */
  @PostMapping("/login/byCode")
  public ServerResponse<Users> loginByCode(
      @RequestBody Map<String, String> map, HttpServletResponse response) {

    var p = checkoutUserExist(map);
    if (p.getStatus() != ResponseCode.SUCCESS.getCode()) {
      return ServerResponse.createByErrorMsg("该用户不存在");
    }
    String code = map.get("code");
    if (code == null) {
      return ServerResponse.createByErrorMsg("验证码不能为空");
    }

    if (map.containsKey("email")) {
      String email = map.get("email");
      if (email == null) {
        return ServerResponse.createByErrorMsg("邮箱不能为空");
      }
      String getCode = redisUtils.get(email + "code");
      if (!Objects.equals(getCode, code)) {
        //        log.info("[Error]登陆失败");
        return ServerResponse.createByErrorMsg("验证码错误");
      }
    } else if (map.containsKey("phoneNum")) {
      String phoneNum = map.get("phoneNum");
      if (phoneNum == null) {
        return ServerResponse.createByErrorMsg("手机号不能为空");
      }
      String getCode = redisUtils.get(phoneNum + "code");
      if (!Objects.equals(getCode, code)) {
        //        log.info("[Error]登陆失败");
        return ServerResponse.createByErrorMsg("验证码错误");
      }
    } else {
      return ServerResponse.createByErrorMsg("登陆失败");
    }

    Users user = p.getData();
    String uid = String.valueOf(user.getUid());
    String token = TokenUtils.getToken(uid);
    //    log.info("[Success]token生成成功");
    // System.out.println(token);
    redisUtils.set(uid, token, 14 * 24 * 60 * 60);
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
    if (password == null) {
      return ServerResponse.createByErrorMsg("密码不能为空");
    }
    String username = map.get("username");
    if (username == null) {
      return ServerResponse.createByErrorMsg("用户名不能为空");
    }
    var p = checkoutUserExist(map);
    if (p.getStatus() == ResponseCode.SUCCESS.getCode()) {
      return ServerResponse.createByErrorMsg("该用户已存在");
    }
    String code = map.get("code");
    if (code == null) {
      return ServerResponse.createByErrorMsg("验证码不能为空");
    }

    if (map.containsKey("email")) {
      String email = map.get("email");
      if (email == null) {
        return ServerResponse.createByErrorMsg("邮箱不能为空");
      }
      String getCode = redisUtils.get(email + "code");
      if (!Objects.equals(getCode, code)) {
        //        log.info("[Error]注册失败");
        return ServerResponse.createByErrorMsg("验证码错误");
      }
    } else if (map.containsKey("phoneNum")) {
      String phoneNum = map.get("phoneNum");
      if (phoneNum == null) {
        return ServerResponse.createByErrorMsg("手机号不能为空");
      }
      String getCode = redisUtils.get(phoneNum + "code");
      if (!Objects.equals(getCode, code)) {
        //        log.info("[Error]注册失败");
        return ServerResponse.createByErrorMsg("验证码错误");
      }
    } else {
      return ServerResponse.createByErrorMsg("注册失败");
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
    usersService.register(newUsers);
    return ServerResponse.createBySuccessMsg("注册成功");
  }

  private ServerResponse<QueryWrapper<Users>> checkHasEmailOrPhoneNum(Map<String, String> map) {
    System.out.println("map:" + map);
    String email = map.get("email");
    String phoneNum = map.get("phoneNum");
    if (email == null && phoneNum == null) {
      return ServerResponse.createByErrorMsg("邮箱或手机号不能为空");
    }
    if (email != null && phoneNum != null) {
      return ServerResponse.createByErrorMsg("只能使用邮箱或手机号");
    }
    QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
    if (email != null) {
      queryWrapper.eq("email", email);
    } else {
      queryWrapper.eq("phone_num", phoneNum);
    }
    return ServerResponse.createBySuccessMsgData("用户存在", queryWrapper);
  }

  private ServerResponse<Users> checkoutUserExist(QueryWrapper<Users> queryWrapper) {
    Users user = usersService.getOne(queryWrapper);
    if (user == null) {
      return ServerResponse.createByErrorMsg("用户不存在");
    }
    return ServerResponse.createBySuccessMsgData("用户已存在", user);
  }

  private ServerResponse<Users> checkoutUserExist(Map<String, String> map) {
    var p = checkHasEmailOrPhoneNum(map);
    if (p.getStatus() != ResponseCode.SUCCESS.getCode()) {
      return ServerResponse.createByErrorMsg(p.getMsg());
    }
    return checkoutUserExist(p.getData());
  }
}
