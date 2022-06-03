package com.xupt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xupt.pojo.MovieHall;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * (MovieHall)表数据库访问层
 *
 * @author ajian
 * @since 2022-06-03 16:47:20
 */
public interface MovieHallMapper extends BaseMapper<MovieHall> {

  /**
   * 批量新增数据（MyBatis原生foreach方法）
   *
   * @param entities List<MovieHall> 实例对象列表
   * @return 影响行数
   */
  int insertBatch(@Param("entities") List<MovieHall> entities);

  /**
   * 批量新增或按主键更新数据（MyBatis原生foreach方法）
   *
   * @param entities List<MovieHall> 实例对象列表
   * @return 影响行数
   * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
   */
  int insertOrUpdateBatch(@Param("entities") List<MovieHall> entities);
}
