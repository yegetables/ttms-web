package com.xupt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.CinemaMoviesMapper;
import com.xupt.dao.UserOrderMapper;
import com.xupt.pojo.CinemaMovies;
import com.xupt.pojo.UserOrder;
import com.xupt.service.CinemaMoviesService;
import com.xupt.utils.BigDecimalUtils;
import java.util.List;
import org.apache.catalina.User;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @since 2022-05-30
 */
@Service
public class CinemaMoviesServiceImpl extends ServiceImpl<CinemaMoviesMapper, CinemaMovies>
    implements CinemaMoviesService {

  @Autowired
  CinemaMoviesMapper cinemaMoviesMapper;
  @Autowired
  UserOrderMapper userOrderMapper;
  @Autowired
  BigDecimalUtils bigDecimalUtils;
  public void insertOrUpdate(UserOrder userOrder) {
    String day = DateFormatUtils.format(userOrder.getMovieStartTime(), "yyyy-MM-dd");
    QueryWrapper<CinemaMovies> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("cinema_id", userOrder.getCinemaId());
    queryWrapper.eq("movie_id", userOrder.getMovieId());
    queryWrapper.eq("day", day);
    QueryWrapper<CinemaMovies> queryWrapper1 = new QueryWrapper<>();
    queryWrapper1.eq("cinema_id", userOrder.getCinemaId());
    queryWrapper1.eq("movie_id", userOrder.getMovieId());
    queryWrapper1.orderByDesc("day");
    List<CinemaMovies> list = cinemaMoviesMapper.selectList(queryWrapper1);
    CinemaMovies cinemaMovies = cinemaMoviesMapper.selectOne(queryWrapper);
    if (cinemaMovies == null && list.size() == 0) {
      cinemaMoviesMapper.insert(
          new CinemaMovies(null, userOrder.getCinemaId(), userOrder.getMovieId(), null,
              userOrder.getTicketMoney(),
              userOrder.getTicketMoney(), day));
    }
    else if(cinemaMovies==null && list.size()!=0){
      Double tmp=bigDecimalUtils.addDouble(list.get(0).getCinemaMovieMoney(),userOrder.getTicketMoney());
      cinemaMoviesMapper.insert(
          new CinemaMovies(null, userOrder.getCinemaId(), userOrder.getMovieId(), null,
              userOrder.getTicketMoney(),
              tmp, day));
    }
    else if(cinemaMovies!=null){
      QueryWrapper<CinemaMovies> queryWrapper2=new QueryWrapper<>();
      queryWrapper2.eq("id",cinemaMovies.getId());
      System.out.println(userOrder.getTicketMoney()+cinemaMovies.getDayMoney());
      cinemaMoviesMapper.update(
          new CinemaMovies(null, userOrder.getCinemaId(), userOrder.getMovieId(), null,
              (double)userOrder.getTicketMoney()+cinemaMovies.getDayMoney(),
              bigDecimalUtils.addDouble(userOrder.getTicketMoney(),cinemaMovies.getCinemaMovieMoney()), day),queryWrapper2);
    }
  }
  public void deleteTicket(List<Long> list){

    for(Long i:list){
         QueryWrapper<UserOrder> queryWrapper=new QueryWrapper<>();
         queryWrapper.eq("id",i);
      UserOrder userOrder = userOrderMapper.selectOne(queryWrapper);
      QueryWrapper<CinemaMovies> queryWrapper1=new QueryWrapper<>();
      String day = DateFormatUtils.format(userOrder.getMovieStartTime(), "yyyy-MM-dd");
      queryWrapper1.eq("cinema_id", userOrder.getCinemaId());
      queryWrapper1.eq("movie_id", userOrder.getMovieId());
      queryWrapper1.eq("day", day);
      CinemaMovies cinemaMovies = cinemaMoviesMapper.selectOne(queryWrapper1);
      cinemaMovies.setDayMoney(bigDecimalUtils.subDouble(cinemaMovies.getDayMoney(),userOrder.getTicketMoney()));
      cinemaMovies.setCinemaMovieMoney(cinemaMovies.getCinemaMovieMoney()-userOrder.getTicketMoney());
      QueryWrapper<CinemaMovies> queryWrapper2=new QueryWrapper<>();
      queryWrapper2.eq("id",cinemaMovies.getId());
      cinemaMoviesMapper.update(cinemaMovies,queryWrapper2);

    }
  }
  public CinemaMovies select(CinemaMovies cinemaMovies){
    QueryWrapper<CinemaMovies> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("cinema_id", cinemaMovies.getCinemaId());
    queryWrapper.eq("movie_id", cinemaMovies.getMovieId());
    queryWrapper.eq("day", cinemaMovies.getDay());
    return cinemaMoviesMapper.selectOne(queryWrapper);
  }
}
