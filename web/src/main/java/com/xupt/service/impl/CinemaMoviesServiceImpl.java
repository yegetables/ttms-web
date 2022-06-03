package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.CinemaMoviesMapper;
import com.xupt.pojo.CinemaMovies;
import com.xupt.service.CinemaMoviesService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @since 2022-05-30
 */
@Service
public class CinemaMoviesServiceImpl extends ServiceImpl<CinemaMoviesMapper, CinemaMovies>
    implements CinemaMoviesService {}
