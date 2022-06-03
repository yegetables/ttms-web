package com.xupt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * (MovieHall)表实体类
 *
 * @author ajian
 * @since 2022-06-03 16:56:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("movie_hall")
public class MovieHall extends Model<MovieHall> {
  // 主键
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  // 电影院的id
  private Integer cinemaId;
  // 座位行数
  private Integer seatLine;
  // 座位列数
  private Integer seatColumn;
  // 演出厅的名字
  private String hallName;
  // 影厅的类型
  private String movieType;

  /**
   * 获取主键值
   *
   * @return 主键值
   */
  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
