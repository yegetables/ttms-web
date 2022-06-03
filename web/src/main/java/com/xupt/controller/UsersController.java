package com.xupt.controller;

import com.xupt.common.ServerResponse;
import com.xupt.pojo.Users;
import com.xupt.service.UsersService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 前端控制器
 *
 * @since 2022-05-30
 */
@Controller
@RequestMapping("/users")
@ResponseBody
public class UsersController {
  @Resource private UsersService usersService;

  /**
   * 获取用户所有信息
   *
   * @param id uid
   * @return 用户信息
   */
  @GetMapping("/getUserInfo")
  public ServerResponse<Users> getUserInfo(@RequestParam("id") Integer id) {
    Users users = usersService.getUserInfo(id);
    if (users != null) return ServerResponse.createBySuccessMsgData("获取用户名成功", users);
    return ServerResponse.createByErrorMsg("获取用户名失败");
  }

  /** 获取所有用户所有信息 */
  @GetMapping("/getAllUsers")
  public ServerResponse<List<Users>> getAllUsers() {
    return ServerResponse.createBySuccessMsgData("获取所有用户信息成功", usersService.getAllUsers());
  }
}
