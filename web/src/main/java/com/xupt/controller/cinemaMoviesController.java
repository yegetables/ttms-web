package com.xupt.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.xupt.pojo.CinemaMovies;
import com.xupt.service.CinemaMoviesService;
import com.xupt.service.impl.CinemaMoviesServiceImpl;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/cinemaMovies")
@ResponseBody
public class cinemaMoviesController extends ApiController {
    @Resource
    CinemaMoviesServiceImpl service;
    @GetMapping
  public R select(@RequestBody CinemaMovies cinemaMovies){
      CinemaMovies select = service.select(cinemaMovies);
       return success(select);
    }
}
