package com.xupt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/** @since 2022-05-30 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("cinema_movies")
@AllArgsConstructor
public class CinemaMovies extends Model<CinemaMovies> {

  private static final long serialVersionUID = 1L;

  /** 主键 */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  /** 电影院的id */
  private Integer cinemaId;

  /** 电影id */
  private Integer movieId;

  /** 电影的最低价格 */
  private Double movieLowmoney;

  /** 电影院这部电影当天赚得钱 */
  private Double dayMoney;

  /** 电影院这部电影上映时间总钱数 */
  private Double cinemaMovieMoney;
  /** 当天的日期 */
  @JsonFormat(pattern = "yyyy-MM-dd")
  private String day;
}
