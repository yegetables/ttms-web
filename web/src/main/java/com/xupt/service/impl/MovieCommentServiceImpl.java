package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.MovieCommentMapper;
import com.xupt.pojo.MovieComment;
import com.xupt.service.MovieCommentService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @since 2022-05-30
 */
@Service
public class MovieCommentServiceImpl extends ServiceImpl<MovieCommentMapper, MovieComment>
    implements MovieCommentService {}
