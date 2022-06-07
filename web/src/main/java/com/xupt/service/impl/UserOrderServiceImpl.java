package com.xupt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.HallSeatMapper;
import com.xupt.dao.MovieMapper;
import com.xupt.dao.UserOrderMapper;
import com.xupt.pojo.HallSeat;
import com.xupt.pojo.Movie;
import com.xupt.pojo.UserOrder;
import com.xupt.service.UserOrderService;
import com.xupt.utils.BigDecimalUtils;
import com.xupt.utils.RedisUtils;
import java.util.List;
import javax.management.QueryEval;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (UserOrder)表服务实现类
 *
 * @author ajian
 * @since 2022-06-03 17:04:50
 */
@Service("userOrderService")
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder>
    implements UserOrderService {
  @Autowired
  RedisUtils redisUtils;
  @Autowired
  HallSeatMapper hallSeatMapper;
  @Autowired
  UserOrderMapper userOrderMapper;
  @Autowired
  MovieMapper movieMapper;
  @Autowired
  BigDecimalUtils bigDecimalUtils;
  public boolean buyTicket(UserOrder userOrder,HallSeat hallSeat){
    try {
      userOrderMapper.insert(userOrder);
      QueryWrapper<UserOrder> orderQueryWrapper = new QueryWrapper<>(userOrder);
      UserOrder userOrder1 = userOrderMapper.selectOne(orderQueryWrapper);
      QueryWrapper<HallSeat> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("seat_line", hallSeat.getSeatLine());
      queryWrapper.eq("seat_column", hallSeat.getSeatColumn());
      queryWrapper.eq("movie_plan_id", hallSeat.getMoviePlanId());
      HallSeat hallSeat1 = hallSeatMapper.selectOne(queryWrapper);
      hallSeat1.setTicketStatus(1);
      hallSeat1.setOrderId(userOrder1.getId());
      hallSeatMapper.update(hallSeat1, queryWrapper);
      QueryWrapper<Movie> movieQueryWrapper=new QueryWrapper<>();
      movieQueryWrapper.eq("id",userOrder.getMovieId());
      Movie movie = movieMapper.selectOne(movieQueryWrapper);
      movie.setMovieMoney(bigDecimalUtils.addDouble(movie.getMovieMoney(),userOrder.getOrderMoney()));
      movieMapper.update(movie,movieQueryWrapper);
      return true;
    }catch (Exception e){
      return false;
    }
  }

  @Override
  public boolean returnTicket(UserOrder userOrder) {
    try{
      int id=userOrder.getId();
      QueryWrapper<HallSeat> queryWrapper=new QueryWrapper<>();
      queryWrapper.eq("order_id",id);
      HallSeat hallSeat=hallSeatMapper.selectOne(queryWrapper);
      hallSeat.setOrderId(-1);
      hallSeat.setTicketStatus(-1);
      hallSeatMapper.update(hallSeat,queryWrapper);
      QueryWrapper<UserOrder> orderQueryWrapper=new QueryWrapper<>();
      orderQueryWrapper.eq("id",userOrder.getId());
      userOrderMapper.delete(orderQueryWrapper);
      QueryWrapper<Movie> movieQueryWrapper=new QueryWrapper<>();
      movieQueryWrapper.eq("id",userOrder.getMovieId());
      Movie movie = movieMapper.selectOne(movieQueryWrapper);
      movie.setMovieMoney(bigDecimalUtils.subDouble(movie.getMovieMoney(),userOrder.getOrderMoney()));
      movieMapper.update(movie,movieQueryWrapper);
      return true;
    }catch (Exception e){
      return false;
    }
  }

}
