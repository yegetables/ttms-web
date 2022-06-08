package com.xupt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.pojo.HallSeat;
import com.xupt.pojo.UserOrder;
import com.xupt.service.UserOrderService;
import com.xupt.service.impl.CinemaMoviesServiceImpl;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (UserOrder)表控制层
 *
 * @author ajian
 * @since 2022-06-03 17:04:50
 */
@Controller
@RequestMapping("/userOrder")
@ResponseBody
public class UserOrderController extends ApiController {
  /** 服务对象 */
  @Resource private UserOrderService userOrderService;

  @Resource private CinemaMoviesServiceImpl cinemaMoviesService;
  /**
   * 分页查询所有数据
   *
   * @param userOrderAndPage(page) 分页对象
   * @param userOrderAndPage(userOrder) 查询实体
   * @return 所有数据
   */
  @PostMapping
  public R selectAll(@RequestBody UserOrderAndPage<UserOrder> userOrderAndPage) {
    UserOrder userOrder = userOrderAndPage.getUserOrder();
    Page<UserOrder> page = userOrderAndPage.getPage();
    return success(this.userOrderService.page(page, new QueryWrapper<>(userOrder)));
  }

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("{id}")
  public R selectOne(@PathVariable Serializable id) {
    return success(this.userOrderService.getById(id));
  }

  /**
   * 新增数据
   *
   * @param userOrder 实体对象
   * @return 新增结果
   */
  @PostMapping("/new")
  public R insert(@RequestBody UserOrder userOrder) {
    try {
      boolean isSuccess = this.userOrderService.save(userOrder);
      if (isSuccess) {
        return success(userOrder);
      }
    } catch (Exception e) {
      return failed("新增失败" + e.getMessage());
    }
    return failed("新增失败");
  }

  /**
   * 修改数据
   *
   * @param userOrder 实体对象
   * @return 修改结果
   */
  @PutMapping
  public R update(@RequestBody UserOrder userOrder) {
    try {

      boolean isSuccess = this.userOrderService.updateById(userOrder);
      if (isSuccess) {
        userOrder = this.userOrderService.getById(userOrder.getId());
        return success(userOrder);
      }
    } catch (Exception e) {
      return failed("修改失败" + e.getMessage());
    }
    return failed("修改失败");
  }

  /**
   * 删除数据
   *
   * @param idList 主键结合
   * @return 删除结果
   */
  @DeleteMapping
  public R delete(@RequestParam("idList") List<Long> idList) {
    cinemaMoviesService.deleteTicket(idList);
    return success(this.userOrderService.removeByIds(idList));
  }
  /** 购票 */
  @PostMapping("/new/buyTicket")
  public R buyTicket(@RequestBody UserOrderAndSeat userOrderAndSeat) {
    try {
      UserOrder userOrder = userOrderAndSeat.getUserOrder();
      HallSeat hallSeat = userOrderAndSeat.getHallSeat();
      if (userOrderService.buyTicket(userOrder, hallSeat)) return success(null);

    } catch (Exception e) {
      logger.error(e.toString());
      return failed("服务器异常");
    }
    return failed("服务器异常");
  }

  @DeleteMapping("/returnTicket")
  public R returnTicket(@RequestBody UserOrder userOrder) {
    try {
      if (userOrderService.returnTicket(userOrder)) {
        return success(null);
      }
    } catch (Exception e) {
      return failed("服务器异常");
    }
    return failed("服务器异常");
  }
}

@Data
class UserOrderAndPage<T> {
  private UserOrder userOrder;
  private Page<T> page;
}

@Data
class UserOrderAndSeat {
  private UserOrder userOrder;
  private HallSeat hallSeat;
}
