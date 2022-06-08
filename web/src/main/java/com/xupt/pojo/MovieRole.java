package com.xupt.pojo;

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
@TableName("movie_role")
public class MovieRole extends Model<MovieRole> {

  private static final long serialVersionUID = 1L;

  @TableId(value = "role_id")
  private Integer roleId;

  private String roleName;
}
