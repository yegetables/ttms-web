package com.xupt.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
@TableName("movie_hall")
public class MovieHall {

  private long id;
  private long cinemaId;
  private long seatLine;
  private long seatColumn;
  private String hallName;
  private String movieType;
}
