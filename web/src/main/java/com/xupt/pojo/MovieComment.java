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
@TableName("movie_comment")
public class MovieComment extends Model<MovieComment> {

  private static final long serialVersionUID = 1L;

  /** 主键id */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  /** 电影的id */
  private Integer movieId;

  /** 电影的评论 */
  private String peopleComment;

  /** 评论的人的昵称 */
  private String peopleNickname;

  /** 图像 */
  private String peopleIcon;

  /** 对电影的个人评分 */
  private Float peopleScore;
}
