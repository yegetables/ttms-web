package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.MovieDirectorMapper;
import com.xupt.pojo.MovieDirector;
import com.xupt.service.IMovieDirectorService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * 
 * @since 2022-05-30
 */
@Service
public class MovieDirectorServiceImpl extends ServiceImpl<MovieDirectorMapper, MovieDirector>
    implements IMovieDirectorService {}
