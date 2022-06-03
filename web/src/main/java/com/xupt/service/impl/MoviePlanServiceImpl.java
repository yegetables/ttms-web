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
    queryWrapper.eq("cinema_id", id);
    queryWrapper.ge("movie_start_time", nowDate);
    List<MoviePlan> moviePlans = moviePlanMapper.selectList(queryWrapper);
    return moviePlans;
  }

  public List<MoviePlan> getMovieListByDate(String date) {
    String nowDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd" + " 00:00:00");

    // String nowDay=DateFormatUtils.format(new Date(),"yyyy-MM-dd");
    List<MoviePlan> movieListByDate = moviePlanMapper.getMovieListByDate(nowDate, date);
    return movieListByDate;
  }

  public void update(MoviePlan plan) {
    QueryWrapper<MoviePlan> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("id", plan.getId());
    moviePlanMapper.update(plan, queryWrapper);
  }

  public void initSeat(MoviePlan plan) {
    QueryWrapper<MovieHall> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("id", plan.getHallId());
    MovieHall hall = movieHallMapper.selectOne(queryWrapper);
    System.out.println(hall);
    int column = hall.getSeatColumn();
    int line = hall.getSeatLine();
    for (int i = 1; i <= column; i++)
      for (int j = 1; j <= line; j++) {
        HallSeat hallSeat = new HallSeat();
        hallSeat.setMoviePlanId(plan.getId());
        hallSeat.setSeatColumn(i);
        hallSeat.setSeatLine(j);
        hallSeat.setTicketStatus(1);
        hallSeat.setOrderId(-1);
        hallSeatMapper.insert(hallSeat);
      }
  }

  public void deleteSeat(Integer id) {
    QueryWrapper<MoviePlan> queryWrapper3 = new QueryWrapper<>();
    queryWrapper3.eq("id", id);
    MoviePlan plan = moviePlanMapper.selectOne(queryWrapper3);
    QueryWrapper<MovieHall> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("id", plan.getHallId());
    MovieHall hall = movieHallMapper.selectOne(queryWrapper);
    int column = hall.getSeatColumn();
    int line = hall.getSeatLine();
    for (int i = 1; i <= column; i++)
      for (int j = 1; j <= line; j++) {
        QueryWrapper<HallSeat> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("seat_column", i);
        queryWrapper2.eq("seat_line", j);
        hallSeatMapper.delete(queryWrapper2);
      }
  }

  public void delete(Integer id) {
    QueryWrapper<MoviePlan> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("id", id);
    moviePlanMapper.delete(queryWrapper);
  }
}
