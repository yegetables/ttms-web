package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.MoviePlanMapper;
import com.xupt.pojo.MoviePlan;
import com.xupt.service.IMoviePlanService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @since 2022-05-30
 */
@Service
public class MoviePlanServiceImpl extends ServiceImpl<MoviePlanMapper, MoviePlan>
    implements IMoviePlanService {}
