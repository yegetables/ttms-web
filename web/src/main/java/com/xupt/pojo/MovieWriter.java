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
 * @author ${author}
 * @since 2022-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("movie_writer")
public class MovieWriter extends Model<MovieWriter> {

  private static final long serialVersionUID = 1L;

  /** 主键 */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  /** 电影的id */
  private Integer movieId;

  /** 编剧的照片 */
  private String screenwriterPicture;

  /** 编剧的名字 */
  private String screenwriterName;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
