package com.xupt.controller;

import com.xupt.dao.UserMapper;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

  @Autowired private UserMapper userMapper;
  @Autowired private RedisTemplate redisTemplate;

  @RequestMapping("/getUserName")
  @ResponseBody
  public String getUserName(Integer id) {
    return userMapper.getUserName(id);
  }

  @PostMapping("/login")
  @ResponseBody
  public String login(@RequestBody Map<String, String> map) {
    if (map.containsKey("username") && map.containsKey("password")) {
      return "success";
    } else {
      return "username or password is null  " + "you send[" + map.toString() + "]";
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
