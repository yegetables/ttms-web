package com.xupt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.pojo.UserOrder;
import com.xupt.service.UserOrderService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

  /**
   * 分页查询所有数据
   *
   * @param page 分页对象
   * @param userOrder 查询实体
   * @return 所有数据
   */
  @GetMapping
  public R selectAll(Page<UserOrder> page, UserOrder userOrder) {
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
  @PostMapping
  public R insert(@RequestBody UserOrder userOrder) {
    return success(this.userOrderService.save(userOrder));
  }

  /**
   * 修改数据
   *
   * @param userOrder 实体对象
   * @return 修改结果
   */
  @PutMapping
  public R update(@RequestBody UserOrder userOrder) {
    return success(this.userOrderService.updateById(userOrder));
  }

  /**
   * 删除数据
   *
   * @param idList 主键结合
   * @return 删除结果
   */
  @DeleteMapping
  public R delete(@RequestParam("idList") List<Long> idList) {
    return success(this.userOrderService.removeByIds(idList));
  }
}
