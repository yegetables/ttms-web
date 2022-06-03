package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.HallSeatMapper;
import com.xupt.pojo.HallSeat;
import com.xupt.service.HallSeatService;
import org.springframework.stereotype.Service;

/**
 * (HallSeat)表服务实现类
 *
 * @author ajian
 * @since 2022-06-03 14:34:32
 */
@Service("hallSeatService")
public class HallSeatServiceImpl extends ServiceImpl<HallSeatMapper, HallSeat>
    implements HallSeatService {}
