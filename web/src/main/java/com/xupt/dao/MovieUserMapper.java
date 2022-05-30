package com.xupt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xupt.pojo.MovieUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表 Mapper 接口
 *
 * @since 2022-05-30
 */
@Mapper
public interface MovieUserMapper extends BaseMapper<MovieUser> {}
