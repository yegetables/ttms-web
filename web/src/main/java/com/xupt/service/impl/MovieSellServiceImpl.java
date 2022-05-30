package com.xupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.dao.MovieSellMapper;
import com.xupt.pojo.MovieSell;
import com.xupt.service.IMovieSellService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * 
 * @since 2022-05-30
 */
@Service
public class MovieSellServiceImpl extends ServiceImpl<MovieSellMapper, MovieSell>
    implements IMovieSellService {}
