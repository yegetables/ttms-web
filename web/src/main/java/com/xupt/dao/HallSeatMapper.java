package com.xupt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xupt.configer.MybatisRedisCache;
import com.xupt.pojo.HallSeat;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * (HallSeat)表数据库访问层
 *
 * @author ajian
 * @since 2022-06-03 14:37:43
 */

public interface HallSeatMapper extends BaseMapper<HallSeat> {}
