package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.MovieMapper;
import com.xupt.pojo.Movie;
import com.xupt.service.IMovieService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * 
 * @since 2022-05-30
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements IMovieService {}
