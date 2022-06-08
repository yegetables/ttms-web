package com.xupt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * (HallSeat)表实体类
 *
 * @author ajian
 * @since 2022-06-03 14:44:16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hall_seat")
public class HallSeat extends Model<HallSeat> {
  // 主键
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  // 座位的行
  private Integer seatLine;
  // 座位的列
  private Integer seatColumn;
  // 演出计划Id
  private Integer moviePlanId;
  // 订单id
  private Integer orderId;
  // 票是否已售
  private Integer ticketStatus;
}
