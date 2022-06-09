package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.UsersMapper;
import com.xupt.pojo.Users;
import com.xupt.service.UsersService;
import com.xupt.utils.PHPass;
import com.xupt.utils.UsersUtils;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务实现类
 *
 * @since 2022-05-30
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

  @Resource private UsersMapper usersMapper;
  @Resource private PHPass phPass;

  @Override
  public Users getUserInfo(Integer id) {
    Users user = usersMapper.selectById(id);
    // 去除敏感信息
    user.setPassword(null);
    return user;
  }

  @Override
  @Transactional
  public Users register(Map<String, String> map) {
    Users newUsers = new Users();
    String password = map.get("password");
    String username = map.get("username");

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
    usersMapper.insert(newUsers);
    return usersMapper.selectById(newUsers.getUid());
  }

  @Override
  public List<Users> getAllUsers() {
    var list = usersMapper.selectList(null);
    list.forEach(UsersUtils::removeSecret);
    return list;
  }
}
