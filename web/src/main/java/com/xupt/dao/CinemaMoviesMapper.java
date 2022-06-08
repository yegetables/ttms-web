package com.xupt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xupt.configer.MybatisRedisCache;
import com.xupt.pojo.CinemaMovies;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * Mapper 接口
 *
 * @since 2022-05-30
 */
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface CinemaMoviesMapper extends BaseMapper<CinemaMovies> {}
