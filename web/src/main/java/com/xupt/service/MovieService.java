package com.xupt.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xupt.pojo.Movie;
import com.xupt.pojo.SortRuleType;
import java.util.List;

/**
 * 服务类
 *
 * @since 2022-05-30
 */
public interface MovieService extends IService<Movie> {
  List<Movie> queryMovieList(String type, String rule, int page, int pageLimit);

  Page<Movie> queryMovieListAndSort(Page<Movie> page, SortRuleType sortRuleType);
}
