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

  boolean deleteAll(List<Long> idList);

  Page<Movie> queryMovieListAndSort(Page<Movie> page, SortRuleType sortRuleType);
}
