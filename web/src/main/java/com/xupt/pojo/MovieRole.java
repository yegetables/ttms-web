package com.xupt.pojo;

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
@TableName("movie_role")
public class MovieRole extends Model<MovieRole> {

  private static final long serialVersionUID = 1L;

  private Integer roleId;

  private String roleName;

  @Override
  protected Serializable pkVal() {
    return null;
  }
}
