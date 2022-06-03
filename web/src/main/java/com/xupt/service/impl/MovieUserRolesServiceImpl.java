package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.MovieUserRolesMapper;
import com.xupt.pojo.MovieUserRoles;
import com.xupt.service.MovieUserRolesService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @since 2022-05-30
 */
@Service
public class MovieUserRolesServiceImpl extends ServiceImpl<MovieUserRolesMapper, MovieUserRoles>
    implements MovieUserRolesService {}
