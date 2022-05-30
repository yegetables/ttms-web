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
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2022-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_order")
public class UserOrder extends Model<UserOrder> {

    private static final long serialVersionUID=1L;

    /**
     * 主键也是订单号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 使用者的id
     */
    private Integer userId;

    /**
     * 电影院的名字
     */
    private String cinemaName;

    /**
     * 电影的名字
     */
    private String movieName;

    /**
     * 订单的总钱数
     */
    private Double orderMoney;

    /**
     * 电影院的id
     */
    private Integer cinemaId;

    /**
     * 一张票的钱
     */
    private Float ticketMoney;

    /**
     * 电影的开始时间
     */
    private Date movieStartTime;

    /**
     * 订单状态/未支付or已支付/退款
     */
    private String orderStatus;

    /**
     * 演出厅的名字
     */
    private String hallName;

    /**
     * 电影类型
     */
    private String movieType;

    /**
     * 电影时长
     */
    private Integer movieTime;

    /**
     * 电影的id
     */
    private Integer movieId;

    private String movieHead;

    /**
     * 计划Id
     */
    private Integer planId;

    private Date payTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
