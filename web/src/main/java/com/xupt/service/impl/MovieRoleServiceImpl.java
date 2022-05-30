package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.MovieRoleMapper;
import com.xupt.pojo.MovieRole;
import com.xupt.service.IMovieRoleService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * 
 * @since 2022-05-30
 */
@Service
public class MovieRoleServiceImpl extends ServiceImpl<MovieRoleMapper, MovieRole>
    implements IMovieRoleService {}
