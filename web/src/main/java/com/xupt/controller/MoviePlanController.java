package com.xupt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.common.ApiController;
import com.xupt.common.R;
import com.xupt.pojo.MoviePlan;
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

  @Resource MoviePlanService moviePlanService;

  @PostMapping("/new")
  public R addMoviePlan(@RequestBody MoviePlan plan) {
    if (plan == null
        || plan.getMovieEndTime() == null
        || plan.getMovieStartTime() == null
        || plan.getHallId() == null
        || plan.getCinemaMovieId() == null
        || plan.getTicketMoney() == null) return failed("plan字段填写错误");

    if (moviePlanService.newPlan(plan)) return success(plan);
    return failed("演出计划添加失败");
  }
  // 更改演出计划
  @PutMapping
  public R updateMoviePlan(@RequestBody MoviePlan plan) {
    if (plan == null
        || plan.getId() == null
        || plan.getMovieEndTime() == null
        || plan.getMovieStartTime() == null) return failed("plan字段填写错误");
    if (moviePlanService.update(plan)) {
      plan = moviePlanService.getById(plan.getId());
      return success(plan);
    }
    return failed("更新演出计划失败");
  }

  @PostMapping
  public R selectAll(@RequestBody MoviePlanAndPage<MoviePlan> moviePlanAndPage) {
    if (moviePlanAndPage == null) return failed("参数错误");
    Page<MoviePlan> page = moviePlanAndPage.getPage();
    MoviePlan moviePlan = moviePlanAndPage.getMoviePlan();
    if (page == null) return failed("无page信息");
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
    if (idList == null || idList.size() == 0) return failed("参数错误");
    moviePlanService.deleteAll(idList);
    return success("删除成功");
  }
}

@Data
class MoviePlanAndPage<T> {
  private MoviePlan moviePlan;
  private Page<T> page;
}
