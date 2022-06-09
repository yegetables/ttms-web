package com.xupt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.MovieMapper;
import com.xupt.pojo.Movie;
import com.xupt.pojo.SortRuleType;
import com.xupt.service.MovieService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务实现类
 *
 * @since 2022-05-30
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

  @Resource private MovieMapper movieMapper;

  @Override
  @Transactional
  public boolean deleteAll(List<Long> idList) {
    // plan
    // order
    return false;
  }

  @Override
  public Page<Movie> queryMovieListAndSort(Page<Movie> page, SortRuleType sortRuleType) {
    if (sortRuleType.getSortType() == null) {
      page.addOrder(OrderItem.desc(sortRuleType.getSortRule()));
      Page<Movie> moviePage = movieMapper.selectPage(page, new QueryWrapper<Movie>());
      return moviePage;
    }
    QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("movie_type", sortRuleType.getSortType());
    page.addOrder(OrderItem.desc(sortRuleType.getSortRule()));
    return movieMapper.selectPage(page, queryWrapper);
  }
}
