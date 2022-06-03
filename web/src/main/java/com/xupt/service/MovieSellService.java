package com.xupt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xupt.pojo.Movie;
import com.xupt.pojo.MovieSell;
import java.util.List;

/**
 * (MovieSell)表服务接口
 *
 * @author ajian
 * @since 2022-06-03 17:08:51
 */
public interface MovieSellService extends IService<MovieSell> {
  List<Movie> queryMovieList(String type, String rule, int page, int pageLimit);
}
