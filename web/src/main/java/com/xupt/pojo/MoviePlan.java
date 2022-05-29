package com.xupt.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("movie_plan")
@Component
public class MoviePlan {

  private long id;
  private java.sql.Timestamp movieStartTime;
  private java.sql.Timestamp movieEndTime;
  private java.sql.Date planDate;
  private long cinemaMovieId;
  private long hallId;
  private double ticketMoney;
}
