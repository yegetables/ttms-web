package com.xupt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.MovieMapper;
import com.xupt.dao.MovieSellMapper;
import com.xupt.pojo.Movie;
import com.xupt.pojo.MovieSell;
import com.xupt.service.MovieSellService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * (MovieSell)表服务实现类
 *
 * @author ajian
 * @since 2022-06-03 17:08:51
 */
@Service("movieSellService")
public class MovieSellServiceImpl extends ServiceImpl<MovieSellMapper, MovieSell>
    implements MovieSellService {
  @Resource MovieMapper movieMapper;

  public List<Movie> queryMovieList(String type, String rule, int page, int pageLimit) {
    if (type == null) {

      QueryWrapper queryWrapper = new QueryWrapper();
      Page<Movie> moviePage = new Page<>((page - 1) * pageLimit, pageLimit);
      moviePage.setDesc(rule);
      IPage<Movie> movieList = movieMapper.selectPage(moviePage, queryWrapper);
      return movieList.getRecords();
    }
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.like("movie_type", type);
    Page<Movie> moviePage = new Page<>((page - 1) * pageLimit, pageLimit);
    moviePage.setDesc(rule);
    IPage<Movie> movieList = movieMapper.selectPage(moviePage, queryWrapper);
    return movieList.getRecords();
  }

  public List<Movie> queryMoviesByName(String name, String rule, int page, int pageLimit) {
    QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("movie_name", name);

    Page<Movie> moviePage = new Page<>((page - 1) * pageLimit, pageLimit);
    moviePage.setDesc(rule);
    IPage<Movie> movieList = movieMapper.selectPage(moviePage, queryWrapper);
    return movieList.getRecords();
  }

  public void updateMovie(Movie movie) {
    QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("id", movie.getId());
    movieMapper.update(movie, queryWrapper);
  }
}
