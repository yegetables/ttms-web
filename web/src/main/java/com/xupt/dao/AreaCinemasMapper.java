package com.xupt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xupt.configer.MybatisRedisCache;
import com.xupt.pojo.AreaCinemas;
import org.apache.ibatis.annotations.CacheNamespace;

@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface AreaCinemasMapper extends BaseMapper<AreaCinemas> {}
