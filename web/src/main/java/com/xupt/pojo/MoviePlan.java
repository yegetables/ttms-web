package com.xupt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/** @since 2022-05-30 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("movie_plan")
public class MoviePlan extends Model<MoviePlan> {

  private static final long serialVersionUID = 1L;

  /** 主键 */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  /** 电影的开始时间 */
  private Date movieStartTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  /** 电影的结束时间 */
  private Date movieEndTime;

  @JsonFormat(pattern = "yyyy-MM-dd")
  /** 演出计划的时间 */
  private Date planDate;

  /** 电影id */
  private Integer cinemaMovieId;

  /** 演出厅id */
  private Integer hallId;

  /** 某个时间段的钱 */
  private Double ticketMoney;
}
