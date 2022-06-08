package com.xupt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/** @since 2022-05-30 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("area_cinemas")
public class AreaCinemas extends Model<AreaCinemas> {

  private static final long serialVersionUID = 1L;

  /** 主键id */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  /** 区的id */
  private Integer areaId;

  /** 电影院的名称 */
  private String cinemaName;

  /** 电影院的照片 */
  private String cinemaPicture;

  /** 影院的地址 */
  private String cinemaAddress;

  /** 电影院赚的钱 */
  private Double cinemaMoney;

  /** 电影院的电话号码 */
  private String cinemaNumber;

  /** 电影院的email */
  private String cinemaEmail;
}
