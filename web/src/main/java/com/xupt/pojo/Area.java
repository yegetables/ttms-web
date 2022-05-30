package com.xupt.pojo;

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
@TableName("area")
public class Area extends Model<Area> {

    private static final long serialVersionUID=1L;

    /**
     * 区的id
     */
    private Integer id;

    /**
     * 区的名字
     */
    private String areaName;

    /**
     * 市的id
     */
    private Integer cityId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
