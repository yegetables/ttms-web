package com.xupt;

import com.xupt.dao.UserMapper;
import com.xupt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
        System.out.println("test commit");
      // userMapper.insert(new User(null,"XiAoRayL","0123456","1907402664@qq.com","13181003288",null,20,"男"));


    }

}
