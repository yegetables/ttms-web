package com.xupt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xupt.pojo.Users;
import java.util.List;
import java.util.Map;

/**
 * 服务类
 *
 * @since 2022-05-30
 */
public interface UsersService extends IService<Users> {

  Users getUserInfo(Integer id);

  Users register(Map<String, String> map);

  List<Users> getAllUsers();
}
