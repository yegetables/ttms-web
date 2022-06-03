package com.xupt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.pojo.HallSeat;
import com.xupt.service.HallSeatService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * (HallSeat)表控制层
 *
 * @author ajian
 * @since 2022-06-03 14:34:31
 */
@Controller
@RequestMapping("/hallSeat")
@ResponseBody
public class HallSeatController extends ApiController {
  /** 服务对象 */
  @Resource private HallSeatService hallSeatService;

  /**
   * 分页查询所有数据
   *
   * @param page 分页对象
   * @param hallSeat 查询实体
   * @return 所有数据
   */
  @GetMapping
  public R selectAll(Page<HallSeat> page, HallSeat hallSeat) {
    return success(this.hallSeatService.page(page, new QueryWrapper<>(hallSeat)));
  }

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("{id}")
  public R selectOne(@PathVariable Serializable id) {
    return success(this.hallSeatService.getById(id));
  }

  /**
   * 新增数据
   *
   * @param hallSeat 实体对象
   * @return 新增结果
   */
  @PostMapping
  public R insert(@RequestBody HallSeat hallSeat) {
    boolean isSuccess = this.hallSeatService.save(hallSeat);
    return success(isSuccess ? hallSeat : false);
  }

  /**
   * 修改数据
   *
   * @param hallSeat 实体对象
   * @return 修改结果
   */
  @PutMapping
  public R update(@RequestBody HallSeat hallSeat) {
    return success(this.hallSeatService.updateById(hallSeat));
  }

  /**
   * 删除数据
   *
   * @param idList 主键结合
   * @return 删除结果
   */
  @DeleteMapping
  public R delete(@RequestParam("idList") List<Long> idList) {
    return success(this.hallSeatService.removeByIds(idList));
  }
}
