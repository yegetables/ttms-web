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
 * @author ${author}
 * @since 2022-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("movie_actor")
public class MovieActor extends Model<MovieActor> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 电影的人物图片
     */
    private String actorPicture;

    /**
     * 电影的id
     */
    private Integer movieId;

    /**
     * 真名
     */
    private String actorName;

    /**
     * 角色的名字
     */
    private String roleName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
