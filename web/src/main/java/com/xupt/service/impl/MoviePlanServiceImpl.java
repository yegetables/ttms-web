package com.xupt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.HallSeatMapper;
import com.xupt.dao.MovieHallMapper;
import com.xupt.dao.MovieMapper;
import com.xupt.dao.MoviePlanMapper;
import com.xupt.pojo.HallSeat;
import com.xupt.pojo.Movie;
import com.xupt.pojo.MovieHall;
import com.xupt.pojo.MoviePlan;
import com.xupt.service.MoviePlanService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务实现类
 *
 * @since 2022-05-30
 */
@Service
public class MoviePlanServiceImpl extends ServiceImpl<MoviePlanMapper, MoviePlan>
    implements MoviePlanService {
  @Resource @Lazy MoviePlanServiceImpl moviePlanService;
  @Resource HallSeatMapper hallSeatMapper;
  @Resource MoviePlanMapper moviePlanMapper;
  @Resource MovieHallMapper movieHallMapper;
  @Resource MovieMapper movieMapper;

  private boolean checkDate(MoviePlan plan) {
    SimpleDateFormat sdfAll = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String start = sdfAll.format(plan.getMovieStartTime());
    String end = sdfAll.format(plan.getMovieEndTime());
    String planDate = sdf.format(plan.getPlanDate());
    QueryWrapper<MoviePlan> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .lambda()
        .between(MoviePlan::getMovieStartTime, plan.getMovieStartTime(), plan.getMovieEndTime())
        .or()
        .between(MoviePlan::getMovieEndTime, plan.getMovieStartTime(), plan.getMovieEndTime())
        .and(wrapper -> wrapper.eq(MoviePlan::getHallId, plan.getHallId()));
    List<MoviePlan> plans = moviePlanService.list(queryWrapper);
    if (plans.size() == 0) {
      return true;
    } else {
      return false;
    }
  }

  @Transactional
  public boolean update(@NotNull MoviePlan plan) {
    moviePlanService.deleteOne(plan.getId());
    moviePlanService.newPlan(plan);
    return true;
  }

  private List<HallSeat> getHallSeats(MovieHall hall, Integer planId) {
    if (hall == null) {
      throw new RuntimeException("没有找到演出厅");
    }
    int column = hall.getSeatColumn();
    int line = hall.getSeatLine();
    List<HallSeat> hallSeats = new ArrayList<>(column * line);
    for (int i = 1; i <= column; i++)
      for (int j = 1; j <= line; j++) {
        hallSeats.add(
            new HallSeat()
                .setMoviePlanId(planId)
                .setSeatColumn(i)
                .setSeatLine(j)
                .setTicketStatus(1)
                .setOrderId(-1));
      }
    return hallSeats;
  }

  @Override
  @Transactional
  public boolean newPlan(@NotNull MoviePlan plan) {
    // 检测演出厅存在
    long i =
        movieHallMapper.selectCount(new QueryWrapper<>(new MovieHall().setId(plan.getHallId())));
    if (i != 1L) {
      log.error("[演出计划添加]演出厅不存在");
      return false;
    }
    // 检测电影是否存在
    i = movieMapper.selectCount(new QueryWrapper<>(new Movie().setId(plan.getCinemaMovieId())));
    if (i != 1L) {
      log.error("[演出计划添加]电影不存在");
      return false;
    }
    // 检查演出计划是否重复
    if (!checkDate(plan)) {
      log.error("[演出计划添加]日期冲突");
      return false;
    }
    // 生成
    moviePlanService.save(plan);
    MovieHall hall =
        movieHallMapper.selectOne(new QueryWrapper<>(new MovieHall().setId(plan.getHallId())));
    getHallSeats(hall, plan.getId()).forEach(e -> hallSeatMapper.insert(e));
    return true;
  }

  @Override
  public void deleteAll(@NotNull List<Long> planIdList) {
    for (Long id : planIdList) {
      moviePlanService.deleteOne(Math.toIntExact(id));
    }
  }

  @Transactional
  public void deleteOne(@NotNull Integer planId) {
    // 删除planId
    moviePlanService.removeById(planId);
    // 删除planId对应的座位
    hallSeatMapper.delete(new QueryWrapper<>(new HallSeat().setMoviePlanId(planId)));
  }
}
