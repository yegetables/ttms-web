package com.xupt.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.xupt.pojo.UserOrder;

/**
 * (UserOrder)表数据库访问层
 *
 * @author ajian
 * @since 2022-06-03 17:04:50
 */
public interface UserOrderMapper extends BaseMapper<UserOrder> {

  /**
   * 批量新增数据（MyBatis原生foreach方法）
   *
   * @param entities List<UserOrder> 实例对象列表
   * @return 影响行数
   */
  int insertBatch(@Param("entities") List<UserOrder> entities);

  /**
   * 批量新增或按主键更新数据（MyBatis原生foreach方法）
   *
   * @param entities List<UserOrder> 实例对象列表
   * @return 影响行数
   * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
   */
  int insertOrUpdateBatch(@Param("entities") List<UserOrder> entities);
}
