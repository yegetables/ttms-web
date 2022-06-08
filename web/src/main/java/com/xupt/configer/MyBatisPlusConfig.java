package com.xupt.configer;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfig {

  //  /**
  //   * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false
  //   * 避免缓存出现问题(该属性会在旧插件移除后一同移除)
  //   */
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
    return interceptor;
  }
  //  @Bean
  //  public PaginationInterceptor paginationInterceptor() {
  //    // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
  //    // paginationInterceptor.setOverflow(false);
  //    // 设置最大单页限制数量，默认 500 条，-1 不受限制
  //    // paginationInterceptor.setLimit(500);
  //    // 开启 count 的 join 优化,只针对部分 left join
  //    return new PaginationInterceptor();
  //  }
}
