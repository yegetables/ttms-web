package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.UserOrderMapper;
import com.xupt.pojo.UserOrder;
import com.xupt.service.IUserOrderService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author ${author}
 * @since 2022-05-30
 */
@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder>
    implements IUserOrderService {}
