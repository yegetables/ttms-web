package com.xupt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("movie_hall")
public class MovieHall extends Model<MovieHall> {

  private static final long serialVersionUID = 1L;

  /** 主键 */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  /** 电影院的id */
  private Integer cinemaId;

  /** 座位行数 */
  private Integer seatLine;

  /** 座位列数 */
  private Integer seatColumn;

  /** 演出厅的名字 */
  private String hallName;

  /** 影厅的类型 */
  private String movieType;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
