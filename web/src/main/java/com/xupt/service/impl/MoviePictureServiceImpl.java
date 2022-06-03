package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.MoviePictureMapper;
import com.xupt.pojo.MoviePicture;
import com.xupt.service.MoviePictureService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @since 2022-05-30
 */
@Service
public class MoviePictureServiceImpl extends ServiceImpl<MoviePictureMapper, MoviePicture>
    implements MoviePictureService {}
