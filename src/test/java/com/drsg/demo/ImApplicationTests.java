package com.drsg.demo;

import com.drsg.demo.v1.entity.UserInfo;
import com.drsg.demo.v1.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class ImApplicationTests {
    private final UserInfoService userInfoService;

    @Autowired
    ImApplicationTests(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Test
    void contextLoads() {
        UserInfo userInfo = new UserInfo();
        for (int i = 0; i < 10000; i++) {
            userInfo.setUsername("yxs" + i);
            userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getUsername()));
            this.userInfoService.save(userInfo);
        }
        System.out.println("OK");
    }

}
