package com.xupt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xupt.pojo.MoviePlan;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * 服务类
 *
 * @since 2022-05-30
 */
public interface MoviePlanService extends IService<MoviePlan> {

  boolean update(MoviePlan plan);

  boolean newPlan(@NotNull MoviePlan plan);

  void deleteAll(@NotNull List<Long> idList);
}
