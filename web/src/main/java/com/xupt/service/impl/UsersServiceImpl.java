package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.UsersMapper;
import com.xupt.pojo.Users;
import com.xupt.service.IUsersService;
import com.xupt.utils.UsersUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @since 2022-05-30
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

  @Autowired private UsersMapper usersMapper;

  @Override
  public Users getUserInfo(Integer id) {
    Users user = usersMapper.selectById(id);
    // 去除敏感信息
    user.setPassword(null);
    return user;
  }

  @Override
  public void register(Users newUsers) {
    usersMapper.insert(newUsers);
  }

  @Override
  public List<Users> getAllUsers() {
    var list = usersMapper.selectList(null);
    list.forEach(UsersUtils::removeSecret);
    return list;
  }
}
