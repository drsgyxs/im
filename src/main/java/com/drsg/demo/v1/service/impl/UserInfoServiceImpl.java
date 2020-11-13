package com.drsg.demo.v1.service.impl;

import com.drsg.demo.v1.entity.UserInfo;
import com.drsg.demo.v1.mapper.UserInfoMapper;
import com.drsg.demo.v1.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YXs
 * @since 2020-11-12
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Override
    public UserInfo loadByUsername(String username) {
        return this.baseMapper.loadByUsername(username);
    }
}
