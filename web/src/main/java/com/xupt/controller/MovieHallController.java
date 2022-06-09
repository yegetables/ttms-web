package com.xupt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.common.ApiController;
import com.xupt.common.R;
import com.xupt.pojo.AreaCinemas;
import com.xupt.pojo.MovieHall;
import com.xupt.service.AreaCinemasService;
import com.xupt.service.MovieHallService;
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
 * (MovieHall)表控制层 演出厅
 *
 * @author ajian
 * @since 2022-06-03 16:47:20
 */
@Controller
@RequestMapping("/movieHall")
@ResponseBody
public class MovieHallController extends ApiController {

  /** 服务对象 */
  @Resource private MovieHallService movieHallService;

  @Resource private AreaCinemasService areaCinemasService;
  @Resource private MoviePlanService moviePlanService;

  /**
   * 分页查询所有数据
   *
   * @param movieHallAndPage(Page) 分页对象
   * @param movieHallAndPage(movieHall) 查询实体
   * @return 所有数据
   */
  @PostMapping
  public R selectAll(@RequestBody MovieHallAndPage<MovieHall> movieHallAndPage) {
    if (movieHallAndPage == null) return failed("参数错误");
    Page<MovieHall> page = movieHallAndPage.getPage();
    if (page == null) return failed("参数错误");
    MovieHall movieHall = movieHallAndPage.getMovieHall();
    return success(this.movieHallService.page(page, new QueryWrapper<>(movieHall)));
  }

  /**
   * 新增数据
   *
   * @param movieHall 实体对象
   * @return 新增结果
   */
  @PostMapping("/new")
  public R insert(@RequestBody MovieHall movieHall) {
    if (movieHall == null || movieHall.getCinemaId() == null || movieHall.getId() != null)
      return failed("参数错误");
    AreaCinemas areaCinemas = areaCinemasService.getById(movieHall.getCinemaId());
    if (areaCinemas == null) return failed("电影院不存在");
    boolean isSuccess = this.movieHallService.save(movieHall);
    if (isSuccess) {
      return success(movieHall);
    }
    return failed("新增失败");
  }

  /**
   * 修改数据
   *
   * @param movieHall 实体对象
   * @return 修改结果
   */
  @PutMapping
  public R update(@RequestBody MovieHall movieHall) {
    if (movieHall == null || movieHall.getCinemaId() == null || movieHall.getId() == null)
      return failed("参数错误");
    AreaCinemas areaCinemas = areaCinemasService.getById(movieHall.getCinemaId());
    if (areaCinemas == null) return failed("电影院不存在");
    boolean isSuccess = this.movieHallService.updateById(movieHall);
    if (isSuccess) {
      movieHall = this.movieHallService.getById(movieHall.getId());
      return success(movieHall);
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
    boolean isSuccess = movieHallService.deleteAll(idList);
    if (isSuccess) {
      return success("删除成功");
    }
    return failed("删除失败");
  }
}

@Data
class MovieHallAndPage<T> {
  private MovieHall movieHall;
  private Page<T> page;
}
