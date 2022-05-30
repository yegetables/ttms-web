package com.xupt.pojo;

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
@TableName("people_want")
public class PeopleWant extends Model<PeopleWant> {

  private static final long serialVersionUID = 1L;

  /** 主键id */
  private Integer id;

  /** 电影的id */
  private Integer movieId;

  /** 使用者的账户 */
  private String accounts;

  @Override
  protected Serializable pkVal() {
    return null;
  }
}
