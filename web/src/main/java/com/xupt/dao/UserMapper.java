package com.xupt.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
  public String getUserName(Integer id);
}
