package com.xupt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xupt.common.ServerResponse;
import com.xupt.dao.MovieMapper;
import com.xupt.pojo.Movie;
import com.xupt.service.IMovieService;
import com.xupt.utils.AliyunOSSUtils;
import com.xupt.utils.RedisUtils;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@Log4j2
public class MovieController {

  // 1.查询单个电影 2.按照需求排序电影列表并返回 3.添加电影 4.删除电影
  @Autowired RedisUtils redisUtils;
  @Autowired MovieMapper movieMapper;

  @Autowired AliyunOSSUtils aliyunOSSUtils;
  @Autowired IMovieService movieService;

  /*
   * * 查询单个电影
   */
  @PostMapping("/queryOneMovie")
  public ServerResponse<Movie> queryOneMovie(String id) {
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("id", id);
    log.info("[查询电影]id为：" + id);
    Movie movie = movieMapper.selectOne(queryWrapper);
    if (movie != null) {
      return ServerResponse.createBySuccessMsgData("查询成功", movie);
    }
    return ServerResponse.createByErrorMsg("查询失败");
  }

  /*
   * * 查询电影列表（按照某种规律）
   */
  @PostMapping("/queryMovieList")
  public ServerResponse<List<Movie>> queryMovieList(
      String sortType, String sortRule, int page, int pageLimt) {
    List<Movie> list = movieService.queryMovieList(sortType, sortRule, page, pageLimt);
    return ServerResponse.createBySuccessMsgData("查询成功", list);
  }
}
