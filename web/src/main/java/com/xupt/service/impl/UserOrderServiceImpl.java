package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.UserOrderMapper;
import com.xupt.pojo.UserOrder;
import com.xupt.service.UserOrderService;
import org.springframework.stereotype.Service;

/**
 * (UserOrder)表服务实现类
 *
 * @author ajian
 * @since 2022-06-03 17:04:50
 */
@Service("userOrderService")
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder>
    implements UserOrderService {}
