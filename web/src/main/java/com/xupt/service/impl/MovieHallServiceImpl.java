package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.MovieHallMapper;
import com.xupt.pojo.MovieHall;
import com.xupt.service.MovieHallService;
import java.util.List;
import javax.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (MovieHall)表服务实现类
 *
 * @author ajian
 * @since 2022-06-03 16:47:21
 */
@Service("movieHallService")
public class MovieHallServiceImpl extends ServiceImpl<MovieHallMapper, MovieHall>
    implements MovieHallService {

  @Resource private MovieHallMapper movieHallMapper;
  @Resource private MoviePlanServiceImpl moviePlanService;

  @Override
  @Transactional
  public boolean deleteAll(@NotNull List<Long> idList) {
    // 删除plan和座位
    moviePlanService.deleteAll(idList);
    // 删除演出厅
    removeByIds(idList);
    return true;
  }
}
