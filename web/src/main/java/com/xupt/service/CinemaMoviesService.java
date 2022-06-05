package com.xupt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xupt.pojo.CinemaMovies;
import com.xupt.pojo.UserOrder;
import java.util.List;

public interface CinemaMoviesService extends IService<CinemaMovies> {
  public void insertOrUpdate(UserOrder userOrder);

  public void deleteTicket(List<Long> list);

  public CinemaMovies select(CinemaMovies cinemaMovies);
}
