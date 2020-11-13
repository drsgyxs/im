package com.drsg.demo.v1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drsg.demo.v1.entity.UserInfo;
import com.drsg.demo.v1.service.AuthUserService;
import com.drsg.demo.v1.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserServiceImpl implements AuthUserService {
    private final UserInfoService userInfoService;

    @Autowired
    public AuthUserServiceImpl(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = this.userInfoService.loadByUsername(username);
        System.out.println(userInfo);
        userInfo.getRoles().forEach(System.out::println);
        if (userInfo == null)
            throw new UsernameNotFoundException("用户不存在");
        return User.withUsername(username).password(userInfo.getPassword()).roles("USER").build();
    }
}
