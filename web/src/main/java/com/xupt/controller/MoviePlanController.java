package com.xupt.controller;

import com.xupt.common.ServerResponse;
import com.xupt.pojo.MoviePlan;
import com.xupt.service.impl.MoviePlanServiceImpl;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class MoviePlanController {
  // 1.添加演出计划
  // 2.查询演出计划 演出计划按照时间顺序排序
  // 3.修改演出计划
  // 4.删除演出计划
  // **注意关联座位
  @Autowired MoviePlanServiceImpl moviePlanService;

  @PostMapping("/addMoviePlan")
  public ServerResponse<String> addMoviePlan(MoviePlan plan) {
    if (!moviePlanService.checkDate(plan)) {
      log.error("[演出计划添加]日期冲突");
      return ServerResponse.createByErrorMsg("日期与其它演出计划冲突");
    }
    log.info("[演出计划添加]开始添加");
    moviePlanService.insert(plan);
    moviePlanService.initSeat(plan);
    return ServerResponse.createBySuccessMsg("添加成功");
  }
  // 按照电影id查询演出计划
  @GetMapping("/getMoviePlansListByMovieId")
  public ServerResponse<List<MoviePlan>> getMoviePlansListByMovieId(Integer id) {
    List<MoviePlan> movieListByMovieId = moviePlanService.getMovieListByMovieId(id);
    return ServerResponse.createBySuccessMsgData("查询成功", movieListByMovieId);
  }
  // 根据电影院查询演出计划
  @GetMapping("/getMoviePlansListByCinema")
  public ServerResponse<List<MoviePlan>> getMoviePlanListByCinema(Integer id) {
    List<MoviePlan> moviePlans = moviePlanService.getMovieListByCinemaId(id);
    return ServerResponse.createBySuccessMsgData("查询成功", moviePlans);
  }
  // 根据日期查询演出计划
  @GetMapping("/getMoviePlansByDate")
  public ServerResponse<List<MoviePlan>> getMoviePlanByDate(String date) {
    List<MoviePlan> movieListByDate = moviePlanService.getMovieListByDate(date);
    return ServerResponse.createBySuccessMsgData("查询成功", movieListByDate);
  }
  // 更改演出计划
  @PostMapping("/updateMoviePlan")
  public ServerResponse<String> updateMoviePlan(MoviePlan plan) {
    if (!moviePlanService.checkDate(plan)) {
      return ServerResponse.createByErrorMsg("日期冲突");
    }
    moviePlanService.update(plan);
    return ServerResponse.createBySuccessMsg("修改成功");
  }
  // 删除演出计划
  @PostMapping("/deleteMoviePlan")
  public ServerResponse<String> deleteMoviePlan(Integer id) {
    moviePlanService.deleteSeat(id);
    moviePlanService.delete(id);
    return ServerResponse.createBySuccessMsg("删除成功");
  }
}
