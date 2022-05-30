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
 * 用户表
 *
 * @since 2022-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("movie_user")
public class MovieUser extends Model<MovieUser> {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  /** 用户名 */
  private String accounts;

  /** 密码 */
  private String password;

  /** 昵称 */
  private String nickname;

  /** 注册时间 */
  private Date createTime;

  /** 头像 */
  private String icon;

  /** 性别 */
  private String gender;

  /** 生日 */
  private Date birthday;

  /** 所在城市 */
  private String city;

  /** 职业 */
  private String job;

  /** 个性签名 */
  private String personalizedSignature;

  private Integer cinemaId;

  private Integer sellId;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
