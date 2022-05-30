package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.ProvinceMapper;
import com.xupt.pojo.Province;
import com.xupt.service.IProvinceService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @since 2022-05-30
 */
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province>
    implements IProvinceService {}
