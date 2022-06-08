package com.xupt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.pojo.MoviePlan;
import com.xupt.service.HallSeatService;
import com.xupt.service.MoviePlanService;
import java.util.List;
import javax.annotation.Resource;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端控制器
 *
 * @since 2022-05-30
 */
@Controller
@RequestMapping("/moviePlan")
@Log4j2
@RestController
public class MoviePlanController extends ApiController {
  // 1.添加演出计划
  // 2.查询演出计划 演出计划按照时间顺序排序
  // 3.修改演出计划
  // 4.删除演出计划
  // **注意关联座位
  @Resource MoviePlanService moviePlanService;

  @Resource HallSeatService hallSeatService;

  @PostMapping("/new")
  public R addMoviePlan(@RequestBody MoviePlan plan) {
    if (!moviePlanService.checkDate(plan)) {
      log.error("[演出计划添加]日期冲突");
      return failed("日期与其它演出计划冲突");
    }
    log.info("[演出计划添加]开始添加");
    moviePlanService.newPlan(plan);
    return success(plan);
  }
  // 更改演出计划
  @PutMapping
  public R updateMoviePlan(@RequestBody MoviePlan plan) {
    if (!moviePlanService.checkDate(plan)) {
      return failed("日期冲突");
    }
    moviePlanService.update(plan);
    plan = moviePlanService.getById(plan.getId());
    return success(plan);
  }

  @PostMapping
  public R selectAll(@RequestBody MoviePlanAndPage<MoviePlan> moviePlanAndPage) {
    Page<MoviePlan> page = moviePlanAndPage.getPage();
    MoviePlan moviePlan = moviePlanAndPage.getMoviePlan();
    return success(this.moviePlanService.page(page, new QueryWrapper<>(moviePlan)));
  }

  /**
   * 删除数据
   *
   * @param idList 主键结合
   * @return 删除结果
   */
  @DeleteMapping
  public R delete(@RequestParam("idList") List<Long> idList) {
    boolean isSuccess = this.moviePlanService.removeByIds(idList);
    if (isSuccess) {
      isSuccess = hallSeatService.removeByIds(idList);
      if (isSuccess) {
        return success("删除成功");
      }
      return failed("演出计划删除成功，座位删除失败");
    }
    return failed("删除失败");
  }
}

@Data
class MoviePlanAndPage<T> {
  private MoviePlan moviePlan;
  private Page<T> page;
}
