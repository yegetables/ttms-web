package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.PeopleWantMapper;
import com.xupt.pojo.PeopleWant;
import com.xupt.service.IPeopleWantService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author ${author}
 * @since 2022-05-30
 */
@Service
public class PeopleWantServiceImpl extends ServiceImpl<PeopleWantMapper, PeopleWant>
    implements IPeopleWantService {}
