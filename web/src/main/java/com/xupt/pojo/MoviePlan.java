package com.xupt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 * @since 2022-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("movie_plan")
public class MoviePlan extends Model<MoviePlan> {

  private static final long serialVersionUID = 1L;

  /** 主键 */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  /** 电影的开始时间 */
  private Date movieStartTime;

  /** 电影的结束时间 */
  private Date movieEndTime;

  /** 演出计划的时间 */
  private Date planDate;

  /** 电影院里面对应的电影id */
  private Integer cinemaMovieId;

  /** 该时间段对应的演出厅id */
  private Integer hallId;

  /** 某个时间段的钱 */
  private Float ticketMoney;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
