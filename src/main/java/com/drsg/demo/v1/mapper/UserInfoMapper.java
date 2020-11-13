package com.drsg.demo.v1.mapper;

import com.drsg.demo.v1.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YXs
 * @since 2020-11-12
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    UserInfo loadByUsername(String username);
}
