package com.drsg.demo.v1.service;

import com.drsg.demo.v1.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YXs
 * @since 2020-11-12
 */
public interface UserInfoService extends IService<UserInfo> {
    UserInfo loadByUsername(String username);
}
