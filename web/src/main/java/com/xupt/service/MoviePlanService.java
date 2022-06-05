package com.xupt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xupt.pojo.MoviePlan;
import java.util.List;

/**
 * 服务类
 *
 * @since 2022-05-30
 */
public interface MoviePlanService extends IService<MoviePlan> {
  public boolean checkDate(MoviePlan plan);

  public void insert(MoviePlan plan);

  public List<MoviePlan> getMovieListByMovieId(Integer id);

  public List<MoviePlan> getMovieListByCinemaId(Integer id);

  public List<MoviePlan> getMovieListByDate(String date);

  public void update(MoviePlan plan);

  public void initSeat(MoviePlan plan);

  public void deleteSeat(Integer id);

  public void delete(Integer id);
}
