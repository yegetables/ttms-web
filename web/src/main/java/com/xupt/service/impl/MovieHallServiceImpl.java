package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.MovieHallMapper;
import com.xupt.pojo.MovieHall;
import com.xupt.service.MovieHallService;
import org.springframework.stereotype.Service;

/**
 * (MovieHall)表服务实现类
 *
 * @author ajian
 * @since 2022-06-03 16:47:21
 */
@Service("movieHallService")
public class MovieHallServiceImpl extends ServiceImpl<MovieHallMapper, MovieHall>
    implements MovieHallService {}
