package com.xupt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.HallSeatMapper;
import com.xupt.dao.MovieMapper;
import com.xupt.dao.MoviePlanMapper;
import com.xupt.dao.UserOrderMapper;
import com.xupt.pojo.HallSeat;
import com.xupt.pojo.Movie;
import com.xupt.pojo.MoviePlan;
import com.xupt.pojo.UserOrder;
import com.xupt.service.UserOrderService;
import com.xupt.utils.BigDecimalUtils;
import com.xupt.utils.RedisUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * (UserOrder)表服务实现类
 *
 * @author ajian
 * @since 2022-06-03 17:04:50
 */
@Service("userOrderService")
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder>
    implements UserOrderService {
  @Resource RedisUtils redisUtils;
  @Resource HallSeatMapper hallSeatMapper;
  @Resource UserOrderMapper userOrderMapper;
  @Resource MovieMapper movieMapper;
  @Resource MoviePlanMapper moviePlanMapper;
  @Resource BigDecimalUtils bigDecimalUtils;

  @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
  public boolean buyTicket(UserOrder userOrder, HallSeat hallSeat) {

    userOrderMapper.insert(userOrder);
    hallSeat = hallSeatMapper.selectById(hallSeat.getId());
    if (hallSeat.getTicketStatus() == 0) {
      throw new IllegalArgumentException("座位已售罄");
    }
    hallSeat.setTicketStatus(0);
    hallSeat.setOrderId(userOrder.getId());
    hallSeatMapper.updateById(hallSeat);
    MoviePlan plan =
        moviePlanMapper.selectOne(new QueryWrapper<>(new MoviePlan().setId(userOrder.getPlanId())));
    Movie moive =
        movieMapper.selectOne(new QueryWrapper<>(new Movie().setId(plan.getCinemaMovieId())));
    moive.setDayMoney(moive.getDayMoney() + userOrder.getOrderMoney());
    moive.setMovieMoney(moive.getMovieMoney() + userOrder.getOrderMoney());
    movieMapper.updateById(moive);
    return true;
  }

  @Override
  @Transactional
  public boolean returnTicket(UserOrder userOrder) {
    // order
    // hallseat
    //    try {
    int id = userOrder.getId();
    UserOrder order = userOrderMapper.selectById(id);
    if (order == null) {
      throw new RuntimeException("订单不存在");
    }
    userOrderMapper.deleteById(id);
    HallSeat hallSeat = hallSeatMapper.selectOne(new QueryWrapper<>(new HallSeat().setOrderId(id)));
    if (hallSeat == null) {
      throw new RuntimeException("座位不存在");
    }
    if (hallSeat.getTicketStatus() == 1) {
      throw new RuntimeException("座位已退票");
    } else if (hallSeat.getTicketStatus() == 0) {
      hallSeat.setOrderId(-1);
      hallSeat.setTicketStatus(1);
    }
    hallSeatMapper.updateById(hallSeat);

    Movie moive = movieMapper.selectOne(new QueryWrapper<>(new Movie().setId(order.getMovieId())));
    moive.setDayMoney(moive.getDayMoney() - userOrder.getOrderMoney());
    moive.setMovieMoney(moive.getMovieMoney() - userOrder.getOrderMoney());
    movieMapper.updateById(moive);
    return true;
  }
}
