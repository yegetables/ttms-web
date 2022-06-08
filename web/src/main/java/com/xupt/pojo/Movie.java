package com.xupt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.ArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/** @since 2022-05-30 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("movie")
public class Movie extends Model<Movie> {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  /** 电影名字 */
  private String movieName;

  /** 电影状态(正在热映/即将上映/热播电影) */
  private Integer movieStatus;

  /** 上映时间 */
  private String movieStart;

  /** 电影的时长 */
  private Integer movieMinute;

  /** 电影的简介 */
  private String movieBrief;

  /** 电影的总评分 */
  private Double movieScore;

  /** 电影的票房 */
  private Double movieMoney;

  /** 电影的出产地 */
  private String movieArea;

  /** 电影的类型 */
  private String movieType;

  /** 电影的首页 */
  private String movieHead;

  /** 当天的票房 */
  private Double dayMoney;

  /** 想看的数目 */
  private Long wantLook;

  private String typesArrToString(ArrayList<String> types) {
    StringBuffer rs = new StringBuffer();
    for (String s : types) {
      rs.append(s);
      rs.append("/");
    }
    return rs.toString();
  }
}
