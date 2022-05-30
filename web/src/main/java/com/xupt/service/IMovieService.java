package com.xupt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xupt.pojo.Movie;
import java.util.List;

/**
 * 服务类
 *
 * @since 2022-05-30
 */
public interface IMovieService extends IService<Movie> {
  List<Movie> queryMovieList(String type, String rule, int page, int pageLimit);
}
