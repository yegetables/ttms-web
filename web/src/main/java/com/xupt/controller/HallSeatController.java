package com.xupt.controller;

import com.xupt.common.ServerResponse;
import com.xupt.service.IHallSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 前端控制器
 *
 * @since 2022-05-30
 */
@Controller
@RequestMapping("/hallSeat")
@ResponseBody
public class HallSeatController {
  // 演出厅

  @Autowired private IHallSeatService hallSeatService;

  @GetMapping("/getSeatsNumber")
  public ServerResponse<Integer> getSeatsNumber(@RequestParam Integer id) {
    return ServerResponse.createBySuccessData(hallSeatService.getSeatsNumber(id));
  }
}
