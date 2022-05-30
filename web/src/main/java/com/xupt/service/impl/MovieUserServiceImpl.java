package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.MovieUserMapper;
import com.xupt.pojo.MovieUser;
import com.xupt.service.IMovieUserService;
import org.springframework.stereotype.Service;

/**
 * 用户表 服务实现类
 *
 * 
 * @since 2022-05-30
 */
@Service
public class MovieUserServiceImpl extends ServiceImpl<MovieUserMapper, MovieUser>
    implements IMovieUserService {}
