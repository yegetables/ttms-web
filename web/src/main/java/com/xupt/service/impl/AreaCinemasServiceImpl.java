package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.AreaCinemasMapper;
import com.xupt.pojo.AreaCinemas;
import com.xupt.service.AreaCinemasService;
import org.springframework.stereotype.Service;

/**
 * (AreaCinemas)表服务实现类
 *
 * @author ajian
 * @since 2022-06-09 12:47:26
 */
@Service("areaCinemasService")
public class AreaCinemasServiceImpl extends ServiceImpl<AreaCinemasMapper, AreaCinemas>
    implements AreaCinemasService {}
