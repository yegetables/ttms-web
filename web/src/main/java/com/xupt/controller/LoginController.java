package com.xupt.controller;

import com.xupt.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

  @Autowired private UserMapper userMapper;

  @RequestMapping("/getUserName")
  @ResponseBody
  public String getUserName(Integer id) {
    return userMapper.getUserName(id);
  }
}
