package com.xupt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xupt.pojo.MovieHall;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * (MovieHall)表服务接口
 *
 * @author ajian
 * @since 2022-06-03 16:47:20
 */
public interface MovieHallService extends IService<MovieHall> {
  boolean deleteAll(@NotNull List<Long> idList);
}
