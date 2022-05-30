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
@TableName("hall_seat")
public class HallSeat extends Model<HallSeat> {

  private static final long serialVersionUID = 1L;

  /** 主键 */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  /** 座位的行 */
  private Integer seatLine;

  /** 座位的列 */
  private Integer seatColumn;

  /** 某一个电影院的某个电影的某个时间段的id */
  private Integer moviePlanId;

  /** 订单id */
  private Integer orderId;

  /** 票是否已售 */
  private Integer ticketStatus;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
