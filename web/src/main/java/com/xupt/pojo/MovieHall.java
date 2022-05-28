package com.xupt.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
@TableName("movie_hall")
public class MovieHall {

  private long id;
  private long cinemaId;
  private long seatLine;
  private long seatColumn;
  private String hallName;
  private String movieType;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getCinemaId() {
    return cinemaId;
  }

  public void setCinemaId(long cinemaId) {
    this.cinemaId = cinemaId;
  }


  public long getSeatLine() {
    return seatLine;
  }

  public void setSeatLine(long seatLine) {
    this.seatLine = seatLine;
  }


  public long getSeatColumn() {
    return seatColumn;
  }

  public void setSeatColumn(long seatColumn) {
    this.seatColumn = seatColumn;
  }


  public String getHallName() {
    return hallName;
  }

  public void setHallName(String hallName) {
    this.hallName = hallName;
  }


  public String getMovieType() {
    return movieType;
  }

  public void setMovieType(String movieType) {
    this.movieType = movieType;
  }

}
