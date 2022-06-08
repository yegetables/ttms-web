package com.xupt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.common.ApiController;
import com.xupt.common.R;
import com.xupt.pojo.MovieSell;
import com.xupt.service.MovieSellService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
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
 * (MovieSell)表控制层
 *
 * @author ajian
 * @since 2022-06-03 17:08:51
 */
@Controller
@RequestMapping("/movieSell")
@ResponseBody
public class MovieSellController extends ApiController {
  /** 服务对象 */
  @Resource private MovieSellService movieSellService;

  /**
   * 分页查询所有数据
   *
   * @param page 分页对象
   * @param movieSell 查询实体
   * @return 所有数据
   */
  @GetMapping
  public R selectAll(Page<MovieSell> page, MovieSell movieSell) {
    return success(this.movieSellService.page(page, new QueryWrapper<>(movieSell)));
  }

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("{id}")
  public R selectOne(@PathVariable Serializable id) {
    return success(this.movieSellService.getById(id));
  }

  /**
   * 新增数据
   *
   * @param movieSell 实体对象
   * @return 新增结果
   */
  @PostMapping
  public R insert(@RequestBody MovieSell movieSell) {
    return success(this.movieSellService.save(movieSell));
  }

  /**
   * 修改数据
   *
   * @param movieSell 实体对象
   * @return 修改结果
   */
  @PutMapping
  public R update(@RequestBody MovieSell movieSell) {
    return success(this.movieSellService.updateById(movieSell));
  }

  /**
   * 删除数据
   *
   * @param idList 主键结合
   * @return 删除结果
   */
  @DeleteMapping
  public R delete(@RequestParam("idList") List<Long> idList) {
    return success(this.movieSellService.removeByIds(idList));
  }
}
