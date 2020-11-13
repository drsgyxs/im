package com.drsg.demo.v1.service.impl;

import com.drsg.demo.v1.entity.RoleInfo;
import com.drsg.demo.v1.entity.UserInfo;
import com.drsg.demo.v1.mapper.UserInfoMapper;
import com.drsg.demo.v1.service.AuthUserService;
import com.drsg.demo.v1.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserServiceImpl implements AuthUserService {
    private final UserInfoMapper userInfoMapper;

    @Autowired
    public AuthUserServiceImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = this.userInfoMapper.loadByUsername(username);
        if (userInfo == null)
            throw new UsernameNotFoundException("用户不存在");
        System.out.println(userInfo);
        String[] roles = new String[userInfo.getRoles().size()];
        int i = 0;
        for (RoleInfo role : userInfo.getRoles())
            roles[i++] = role.getRoleName();
        return User.withUsername(username).password(userInfo.getPassword()).roles(roles).build();
    }
}
