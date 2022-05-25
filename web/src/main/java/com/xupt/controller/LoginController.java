package com.xupt.controller;

import com.xupt.dao.UserMapper;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

  @Autowired private UserMapper userMapper;
  @Autowired private RedisTemplate jedisTemplate;

  @RequestMapping("/getUserName")
  @ResponseBody
  public String getUserName(Integer id) {
    return userMapper.getUserName(id);
  }

  @RequestMapping("/login")
  @ResponseBody
  public String login(@RequestBody Map<String, String> map) {
    if (map.containsKey("username") && map.containsKey("password")) {
      return "success";
    } else {
      return "username or password is null  " + "you send[" + map.toString() + "]";
    }
  }

  @RequestMapping("/count")
  @ResponseBody
  public String count(@RequestBody Map<String, String> map) {
    if (map.containsKey("id")) {
      return "" + Integer.valueOf(map.get("id")) + 1;
    } else {
      return "id is null  " + "you send[" + map.toString() + "]";
    }
  }
}
