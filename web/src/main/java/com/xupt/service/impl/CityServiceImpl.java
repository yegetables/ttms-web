package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.CityMapper;
import com.xupt.pojo.City;
import com.xupt.service.CityService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @since 2022-05-30
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {}
