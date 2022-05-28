package com.xupt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xupt.common.ServerResponse;
import com.xupt.dao.UserMapper;
import com.xupt.pojo.User;
import com.xupt.utils.BCrypt;
import com.xupt.utils.MailTools;
import com.xupt.utils.PHPass;
import com.xupt.utils.RandomUtils;
import com.xupt.utils.RedisUtils;
import com.xupt.utils.TokenUtils;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
public class LoginController {

  @Autowired
  private UserMapper userMapper;
  @Autowired
  private RedisUtils redisUtils;
  @Autowired
  private MailTools mailTools;
  @Autowired
  private PHPass phPass;
  // @Autowired private RedisTemplate redisTemplate;
  /*
   * * 处理没有权限
   */

  @RequestMapping("/login403")
  @ResponseBody
  public ServerResponse<Boolean> login403() {
    return ServerResponse.createByErrorMsg("没有权限");
  }

  @RequestMapping("/getUserName")
  @ResponseBody
  public String getUserName(Integer id) {
    return userMapper.getUserName(id);
  }

  @PostMapping("/login/byPassword")
  @ResponseBody
  public ServerResponse<User> login(
      @RequestParam("phoneNum") String phoneNum,
      @RequestParam("password") String password,
      HttpServletResponse response) {
    String queryPassword = userMapper.queryPasswordByPhoneNum(phoneNum);
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("phoneNum", phoneNum);
    User user = userMapper.selectOne(queryWrapper);
    String uid = String.valueOf(user.getUid());
    if (!PHPass.CheckPassword(password, queryPassword)) {
      log.info("[Warn]该用户密码错误");
      return ServerResponse.createByErrorMsg("登录失败");
    }
    // result.put("uid",uid);
    String token = new TokenUtils().getToken(uid);
    log.info("[Success]token生成成功");
    // System.out.println(token);
    redisUtils.set(uid, token, 14 * 24 * 60 * 60);
    response.addHeader("Access-Control-Expose-Headers", "token");
    response.addHeader("token", token);
    log.info("[Success]登录成功");
    return ServerResponse.createBySuccessMsgData("登录成功", user);
  }

  /*
   * * 验证码发送
   */
  @PostMapping("/login/codeSend")
  @ResponseBody
  public ServerResponse<String> codeSend(@RequestParam("phoneNum") String phoneNum) {
    String code = RandomUtils.randomCode();
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("phoneNum", phoneNum);
    User user = userMapper.selectOne(queryWrapper);
    if (user == null) {
      return ServerResponse.createByErrorMsg("该用户不存在");
    }
    String mailAddress = user.getEmail();

    mailTools.sendSimpleMail(mailAddress, code);
    redisUtils.set(phoneNum + "code", code, 60);
    return ServerResponse.createBySuccessMsgData("发送成功", code);
  }

  /*
   * * 验证码登录
   */
  @PostMapping("/login/byCode")
  @ResponseBody
  public ServerResponse<String> loginByCode(
      @RequestParam("phoneNum") String phoneNum,
      @RequestParam("code") String code, HttpServletResponse response) {
    String getCode = redisUtils.get(phoneNum + "code");
    if (!getCode.equals(code)) {
      log.info("[Error]登录失败");
      return ServerResponse.createByErrorMsg("验证码错误");
    }
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("phoneNum", phoneNum);
    User user = userMapper.selectOne(queryWrapper);
    String uid = String.valueOf(user.getUid());
    String token = new TokenUtils().getToken(uid);
    log.info("[Success]token生成成功");
    // System.out.println(token);
    redisUtils.set(uid, token, 14 * 24 * 60 * 60);
    response.addHeader("Access-Control-Expose-Headers", "token");
    response.addHeader("token", token);
    log.info("[Success]登录成功");
    return ServerResponse.createBySuccessMsg("登录成功");
  }

  /*
   * * 注册
   */
  @PostMapping("/register")
  @ResponseBody
  public ServerResponse<String> register(User user) {
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("phoneNum", user.getPhoneNum());
    User tmpUser = userMapper.selectOne(queryWrapper);
    if (tmpUser != null) {
      return ServerResponse.createByErrorMsg("该用户已存在");
    }
    user.setPassword(phPass.HashPassword(user.getPassword()));
    userMapper.insert(user);
    return ServerResponse.createBySuccessMsg("注册成功");
  }

  @RequestMapping(value = "/count", method = RequestMethod.POST)
  @ResponseBody
  public String count(@RequestBody Map<String, String> map) {
    System.out.println("map:" + map);
    if (map.containsKey("id")) {
      return "" + (Integer.parseInt(map.get("id")) + 1);
    } else {
      return "id is null  " + "you send[" + map.toString() + "]";
    }
  }
}
