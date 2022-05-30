package com.xupt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/** @since 2022-05-30 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("cinema_movies")
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
  @TableField("movie_lowMoney")
  private Float movieLowmoney;

  /** 电影院这部电影当天赚得钱 */
  private Float dayMoney;

  /** 电影院这部电影上映时间总钱数 */
  private Float cinemaMovieMoney;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
