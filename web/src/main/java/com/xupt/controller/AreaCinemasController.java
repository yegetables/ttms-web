package com.xupt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.common.ApiController;
import com.xupt.common.R;
import com.xupt.pojo.AreaCinemas;
import com.xupt.service.AreaCinemasService;
import javax.annotation.Resource;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/areaCinemas")
@ResponseBody
public class AreaCinemasController extends ApiController {
  @Resource private AreaCinemasService areaCinemasService;
  /**
   * 分页查询所有数据
   *
   * @param areaCinemasAndPage(page) 分页对象
   * @param areaCinemasAndPage(hallSeat) 查询实体
   * @return 所有数据
   */
  @PostMapping
  public R selectAll(@RequestBody AreaCinemasAndPage<AreaCinemas> areaCinemasAndPage) {
    if (areaCinemasAndPage == null) return failed("参数错误");
    Page<AreaCinemas> page = areaCinemasAndPage.getPage();
    if (page == null) return failed("参数错误");
    AreaCinemas areaCinemas = areaCinemasAndPage.getAreaCinemas();
    return success(areaCinemasService.page(page, new QueryWrapper<>(areaCinemas)));
  }
}

@Data
class AreaCinemasAndPage<T> {
  private Page<T> page;
  private AreaCinemas areaCinemas;
}
