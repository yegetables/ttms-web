package com.xupt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.common.ApiController;
import com.xupt.common.R;
import com.xupt.pojo.HallSeat;
import com.xupt.pojo.MoviePlan;
import com.xupt.service.HallSeatService;
import com.xupt.service.MoviePlanService;
import java.util.List;
import javax.annotation.Resource;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (HallSeat)表控制层 座位
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

  @Resource private MoviePlanService moviePlanService;

  /**
   * 分页查询所有数据
   *
   * @param hallSeatAndPage(page) 分页对象
   * @param hallSeatAndPage(hallSeat) 查询实体
   * @return 所有数据
   */
  @PostMapping
  public R selectAll(@RequestBody HallSeatAndPage<HallSeat> hallSeatAndPage) {
    if (hallSeatAndPage == null) return failed("参数错误");
    Page<HallSeat> page = hallSeatAndPage.getPage();
    if (page == null) return failed("参数错误");
    HallSeat hallSeat = hallSeatAndPage.getHallSeat();
    return success(hallSeatService.page(page, new QueryWrapper<>(hallSeat)));
  }

  /**
   * 新增数据
   *
   * @param hallSeat 实体对象
   * @return 新增结果
   */
  @PostMapping("/new")
  public R insert(@RequestBody HallSeat hallSeat) {
    if (hallSeat == null
        || hallSeat.getSeatLine() == null
        || hallSeat.getSeatColumn() == null
        || hallSeat.getMoviePlanId() == null
        || hallSeat.getTicketStatus() == null) return failed("参数错误");
    if (!checkPlanExist(hallSeat.getMoviePlanId())) return failed("影片计划不存在，请检查影片计划id是否正确");
    boolean isSuccess = this.hallSeatService.save(hallSeat);
    if (isSuccess) {
      return success(hallSeat);
    }
    return failed("新增失败");
  }

  /**
   * 修改数据
   *
   * @param hallSeat 实体对象
   * @return 修改结果
   */
  @PutMapping
  public R update(@RequestBody HallSeat hallSeat) {
    if (hallSeat == null || hallSeat.getId() == null) return failed("参数错误");
    if (hallSeat.getMoviePlanId() != null && !checkPlanExist(hallSeat.getMoviePlanId()))
      return failed("影片计划不存在，请检查影片计划id是否正确");
    boolean isSuccess = this.hallSeatService.updateById(hallSeat);
    if (isSuccess) {
      hallSeat = this.hallSeatService.getById(hallSeat.getId());
      return success(hallSeat);
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
    boolean isSuccess = this.hallSeatService.removeByIds(idList);
    if (isSuccess) {
      return success("删除成功");
    }
    return failed("删除失败");
  }

  private boolean checkPlanExist(Integer moviePlanId) {
    long l =
        moviePlanService.count(new QueryWrapper<MoviePlan>(new MoviePlan().setId(moviePlanId)));
    if (l != 1) return false;
    return true;
  }
}

@Data
class HallSeatAndPage<T> {
  private Page<T> page;
  private HallSeat hallSeat;
}
