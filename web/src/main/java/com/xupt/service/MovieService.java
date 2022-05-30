package com.xupt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.dao.MovieMapper;
import com.xupt.pojo.Movie;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class MovieService {

  @Autowired
  MovieMapper movieMapper;

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
}
