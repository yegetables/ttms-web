package com.xupt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xupt.common.ServerResponse;
import com.xupt.dao.UserMapper;
import java.util.Map;

import com.xupt.pojo.User;
import com.xupt.utils.RedisUtils;
import com.xupt.utils.TokenUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
@Log4j2
@Controller
public class LoginController {

  @Autowired private UserMapper userMapper;
  @Autowired private RedisUtils redisUtils;
 // @Autowired private RedisTemplate redisTemplate;
 /*
  * * 处理没有权限
  */

 @RequestMapping("/login403")
 @ResponseBody
 public ServerResponse<Boolean> login403(){
   return ServerResponse.createByErrorMsg("没有权限");
 }

  @RequestMapping("/getUserName")
  @ResponseBody
  public String getUserName(Integer id) {
    return userMapper.getUserName(id);
  }

  @PostMapping("/login/byPassword")
  @ResponseBody
  public ServerResponse<User> login(@RequestParam("phoneNum") String phoneNum, @RequestParam("password") String password, HttpServletResponse response) {
      String queryPassword = userMapper.queryPasswordByPhoneNum(phoneNum);
      QueryWrapper queryWrapper=new QueryWrapper();
      queryWrapper.eq("phoneNum",phoneNum);
      User user = userMapper.selectOne(queryWrapper);
      String uid=String.valueOf(user.getUid());
      if(password.equals(queryPassword))
      {

          // result.put("uid",uid);
          String token=new TokenUtils().getToken(uid);
          log.info("[Success]token生成成功");
          //System.out.println(token);
          redisUtils.set(uid,token,14*24*60*60);
          response.addHeader("Access-Control-Expose-Headers","token");
          response.addHeader("token",token);
          log.info("[Success]登录成功");
          return ServerResponse.createBySuccessMsgData("登录成功",user);
      }
      else {
          log.info("[Warn]该用户密码错误");
          return ServerResponse.createByErrorMsg("登录失败");
      }
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
