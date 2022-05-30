package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.HallSeatMapper;
import com.xupt.pojo.HallSeat;
import com.xupt.service.IHallSeatService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * 
 * @since 2022-05-30
 */
@Service
public class HallSeatServiceImpl extends ServiceImpl<HallSeatMapper, HallSeat>
    implements IHallSeatService {}
