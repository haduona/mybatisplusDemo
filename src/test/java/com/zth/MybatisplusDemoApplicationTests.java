package com.zth;

import com.zth.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisplusDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void getUser() {
        System.out.println(userMapper.selectList(null));
    }

}
