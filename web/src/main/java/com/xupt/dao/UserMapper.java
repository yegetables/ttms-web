package com.xupt.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xupt.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//@TableName("users")
public interface UserMapper  extends BaseMapper<User> {
  public String getUserName(Integer uid);
  public String queryPasswordByPhoneNum(String phoneNum);
}
