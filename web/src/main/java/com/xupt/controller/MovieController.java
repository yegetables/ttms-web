package com.xupt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.pojo.Movie;
import com.xupt.pojo.SortRuleType;
import com.xupt.service.MovieService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
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

@Controller
@RequestMapping("/movie")
@ResponseBody
@Log4j2
public class MovieController extends ApiController {

  @Resource MovieService movieService;

  /**
   * 分页查询所有数据
   *
   * @param movieAndPageAndSort 分页对象
   * @param movieAndPageAndSort(movie) 查询实体
   * @return 所有数据
   */
  @PostMapping
  public R selectAll(@RequestBody MovieAndPageAndSort<Movie> movieAndPageAndSort) {
    try {
      if (movieAndPageAndSort.getSortRuleType() == null) {
        Page<Movie> page = movieAndPageAndSort.getPage();
        Movie movie = movieAndPageAndSort.getMovie();
        return success(this.movieService.page(page, new QueryWrapper<>(movie)));
      }
      Page<Movie> page = movieAndPageAndSort.getPage();
      SortRuleType sortRuleType = movieAndPageAndSort.getSortRuleType();
      return success(this.movieService.queryMovieListAndSort(page, sortRuleType));
    } catch (Exception e) {
      log.error(e);
      return failed("服务器异常");
    }
  }

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("{id}")
  public R selectOne(@PathVariable Serializable id) {
    return success(this.movieService.getById(id));
  }

  /**
   * 新增数据
   *
   * @param movie 实体对象
   * @return 新增结果
   */
  @PostMapping("/new")
  public R insert(@RequestBody Movie movie) {
    boolean isSuccess = this.movieService.save(movie);
    if (isSuccess) {
      return success(movie);
    }
    return failed("新增失败");
  }

  /**
   * 删除数据
   *
   * @param idList 主键结合
   * @return 删除结果
   */
  @DeleteMapping
  public R delete(@RequestParam("idList") List<Long> idList) {
    boolean isSuccess = this.movieService.removeByIds(idList);
    if (isSuccess) {
      return success("删除成功");
    }
    return failed("删除失败");
  }

  /**
   * 修改数据
   *
   * @param movie 实体对象
   * @return 修改结果
   */
  @PutMapping
  public R update(@RequestBody Movie movie) {
    boolean isSuccess = this.movieService.updateById(movie);
    if (isSuccess) {
      movie = this.movieService.getById(movie.getId());
      return success(movie);
    }
    return failed("修改失败");
  }
}

@Data
class MovieAndPageAndSort<T> {
  private Page<T> page;
  private Movie movie;
  private SortRuleType sortRuleType;
}
