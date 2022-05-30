package com.xupt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xupt.pojo.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
// @TableName("users")
public interface UserMapper extends BaseMapper<Users> {
  public String getUserName(Integer uid);

  public String queryPasswordByPhoneNum(String phoneNum);
}
