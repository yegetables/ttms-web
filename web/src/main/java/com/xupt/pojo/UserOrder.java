package com.xupt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.io.Serializable;

import java.util.Date;
import java.sql.Timestamp;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * (UserOrder)表实体类
 *
 * @author ajian
 * @since 2022-06-03 17:04:50
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_order")
public class UserOrder extends Model<UserOrder> {
  // 主键也是订单号
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  // 使用者的id
  private Integer userId;
  // 电影院的名字
  private String cinemaName;
  // 电影的名字
  private String movieName;
  // 订单的总钱数
  private Double orderMoney;
  // 电影院的id
  private Integer cinemaId;
  // 一张票的钱
  private Double ticketMoney;
  // 电影的开始时间

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date movieStartTime;
  // 订单状态/未支付or已支付/退款
  private String orderStatus;
  // 演出厅的名字
  private String hallName;
  // 电影类型
  private String movieType;
  // 电影时长
  private Integer movieTime;
  // 电影的id
  private Integer movieId;
  private String movieHead;
  // 计划Id
  private Integer planId;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date payTime;

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
