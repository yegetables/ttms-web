package com.xupt.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
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


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public java.sql.Timestamp getMovieStartTime() {
    return movieStartTime;
  }

  public void setMovieStartTime(java.sql.Timestamp movieStartTime) {
    this.movieStartTime = movieStartTime;
  }


  public java.sql.Timestamp getMovieEndTime() {
    return movieEndTime;
  }

  public void setMovieEndTime(java.sql.Timestamp movieEndTime) {
    this.movieEndTime = movieEndTime;
  }


  public java.sql.Date getPlanDate() {
    return planDate;
  }

  public void setPlanDate(java.sql.Date planDate) {
    this.planDate = planDate;
  }


  public long getCinemaMovieId() {
    return cinemaMovieId;
  }

  public void setCinemaMovieId(long cinemaMovieId) {
    this.cinemaMovieId = cinemaMovieId;
  }


  public long getHallId() {
    return hallId;
  }

  public void setHallId(long hallId) {
    this.hallId = hallId;
  }


  public double getTicketMoney() {
    return ticketMoney;
  }

  public void setTicketMoney(double ticketMoney) {
    this.ticketMoney = ticketMoney;
  }

}
