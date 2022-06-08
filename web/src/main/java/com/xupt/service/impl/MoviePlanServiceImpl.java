package com.xupt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.HallSeatMapper;
import com.xupt.dao.MovieHallMapper;
import com.xupt.dao.MoviePlanMapper;
import com.xupt.pojo.HallSeat;
import com.xupt.pojo.MovieHall;
import com.xupt.pojo.MoviePlan;
import com.xupt.service.MoviePlanService;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @since 2022-05-30
 */
@Service
public class MoviePlanServiceImpl extends ServiceImpl<MoviePlanMapper, MoviePlan>
    implements MoviePlanService {
  @Resource MoviePlanMapper moviePlanMapper;
  @Resource MovieHallMapper movieHallMapper;
  @Resource HallSeatMapper hallSeatMapper;

  public boolean checkDate(MoviePlan plan) {
    QueryWrapper<MoviePlan> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .between("movie_start_time", plan.getMovieStartTime(), plan.getMovieEndTime())
        .or()
        .between("movie_end_time", plan.getMovieStartTime(), plan.getMovieEndTime());
    List<MoviePlan> moviePlans = moviePlanMapper.selectList(queryWrapper);
    if (moviePlans.size() != 0) {
      return false;
    }
    return true;
  }

  public void insert(MoviePlan plan) {
    moviePlanMapper.insert(plan);
  }

  public List<MoviePlan> getMovieListByMovieId(Integer id) {
    String nowDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd" + " 00:00:00");

    QueryWrapper<MoviePlan> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("cinema_movie_id", id);
    queryWrapper.ge("movie_start_time", nowDate);
    List<MoviePlan> moviePlans = moviePlanMapper.selectList(queryWrapper);
    return moviePlans;
  }

  public List<MoviePlan> getMovieListByCinemaId(Integer id) {
    String nowDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd" + " 00:00:00");
    QueryWrapper<MoviePlan> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("cinema_movie_id", id);
    queryWrapper.ge("movie_start_time", nowDate);
    List<MoviePlan> moviePlans = moviePlanMapper.selectList(queryWrapper);
    return moviePlans;
  }

  public List<MoviePlan> getMovieListByDate(String date) {
    String nowDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd" + " 00:00:00");
    List<MoviePlan> movieListByDate = moviePlanMapper.getMovieListByDate(nowDate, date);
    return movieListByDate;
  }

  public void update(MoviePlan plan) {
    if (plan == null) return;
    moviePlanMapper.update(plan, new QueryWrapper<>(new MoviePlan().setId(plan.getId())));
  }

  public void initSeat(MoviePlan plan) {
    if (plan == null) return;
    MovieHall hall =
        movieHallMapper.selectOne(new QueryWrapper<>(new MovieHall().setId(plan.getHallId())));
    if (hall == null) return;
    int column = hall.getSeatColumn();
    int line = hall.getSeatLine();
    for (int i = 1; i <= column; i++)
      for (int j = 1; j <= line; j++) {
        HallSeat hallSeat =
            new HallSeat()
                .setMoviePlanId(plan.getId())
                .setSeatColumn(i)
                .setSeatLine(j)
                .setTicketStatus(1)
                .setOrderId(-1);
        hallSeatMapper.insert(hallSeat);
      }
  }

  public void deleteSeat(Integer id) {
    MoviePlan plan = moviePlanMapper.selectOne(new QueryWrapper<>(new MoviePlan().setId(id)));
    if (plan == null) return;
    MovieHall hall =
        movieHallMapper.selectOne(new QueryWrapper<>(new MovieHall().setId(plan.getHallId())));
    if (hall == null) return;
    int column = hall.getSeatColumn();
    int line = hall.getSeatLine();
    for (int i = 1; i <= column; i++)
      for (int j = 1; j <= line; j++) {
        hallSeatMapper.delete(new QueryWrapper<>(new HallSeat().setSeatColumn(i).setSeatLine(j)));
      }
  }

  public void delete(Integer id) {
    moviePlanMapper.delete(new QueryWrapper<>(new MoviePlan().setId(id)));
  }

  @Override
  public void newPlan(MoviePlan plan) {
    insert(plan);
    initSeat(plan);
  }
}
