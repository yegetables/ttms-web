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
 * <p>
 * 
 * </p>
 *
 * 
 * @since 2022-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("users")
public class Users extends Model<Users> {

    private static final long serialVersionUID=1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    private String username;

    private String password;

    private String email;

    private String phoneNum;

    private String headUrl;

    private Integer age;

    private String gender;


    @Override
    protected Serializable pkVal() {
        return this.uid;
    }

}
