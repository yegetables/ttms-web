package com.xupt.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.stereotype.Component;

@TableName("movie")
@Component
public class Movie {
  private String movie_name;
  private String movie_status;
  private int movie_minute;
  private String movie_brief;//电影简介
  private String movie_score;
  private String movie_money;
  private String movie_area;
  private String movie_type;
  private String movie_head;
  private double day_money;
  private int want_look;
}
