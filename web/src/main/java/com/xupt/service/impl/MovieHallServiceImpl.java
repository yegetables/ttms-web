package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.MovieHallMapper;
import com.xupt.pojo.MovieHall;
import com.xupt.service.IMovieHallService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * 
 * @since 2022-05-30
 */
@Service
public class MovieHallServiceImpl extends ServiceImpl<MovieHallMapper, MovieHall>
    implements IMovieHallService {}
