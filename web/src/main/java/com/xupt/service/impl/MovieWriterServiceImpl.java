package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.MovieWriterMapper;
import com.xupt.pojo.MovieWriter;
import com.xupt.service.IMovieWriterService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * 
 * @since 2022-05-30
 */
@Service
public class MovieWriterServiceImpl extends ServiceImpl<MovieWriterMapper, MovieWriter>
    implements IMovieWriterService {}
