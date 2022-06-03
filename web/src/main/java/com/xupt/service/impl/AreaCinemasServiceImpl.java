package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.AreaCinemasMapper;
import com.xupt.pojo.AreaCinemas;
import com.xupt.service.AreaCinemasService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @since 2022-05-30
 */
@Service
public class AreaCinemasServiceImpl extends ServiceImpl<AreaCinemasMapper, AreaCinemas>
    implements AreaCinemasService {}
