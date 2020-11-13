package com.drsg.demo.v1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drsg.demo.v1.entity.Page;
import com.drsg.demo.v1.entity.UserInfo;
import com.drsg.demo.v1.mapper.UserInfoMapper;
import com.drsg.demo.v1.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YXs
 * @since 2020-11-12
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Override
    public Page<UserInfo> userPage(Page<UserInfo> page, UserInfo userInfo) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        if (userInfo != null)
            queryWrapper
                    .like(StringUtils.isNotEmpty(userInfo.getUsername()), "USERNAME", userInfo.getUsername())
                    .eq(StringUtils.isNotEmpty(userInfo.getSex()), "SEX", userInfo.getSex())
                    .eq(StringUtils.isNotEmpty(userInfo.getEnabled()), "ENABLED", userInfo.getEnabled());
        this.baseMapper.selectPage(page, queryWrapper);
        return page;
    }
}
