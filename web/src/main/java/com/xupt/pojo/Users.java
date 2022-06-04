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
 * (Users)表实体类
 *
 * @author ajian
 * @since 2022-06-04 19:40:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("users")
public class Users extends Model<Users> {

  private static final long serialVersionUID = 1L;

  @TableId(value = "uid", type = IdType.AUTO)
  private Integer uid;

  private String username;
  private String password;
  private String email;
  private String phoneNum;
  private String headUrl;
  private Integer age;
  private String gender;

  /**
   * 获取主键值
   *
   * @return 主键值
   */
  @Override
  protected Serializable pkVal() {
    return this.uid;
  }
}
