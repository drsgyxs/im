package com.drsg.demo.v1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drsg.demo.v1.entity.Page;
import com.drsg.demo.v1.entity.Result;
import com.drsg.demo.v1.entity.UserInfo;
import com.drsg.demo.v1.service.UserInfoService;
import com.drsg.demo.v1.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YXs
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/v1/users")
public class UserInfoController {
    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Result<Page<UserInfo>> userList(Page pageInfo) {
        Page<UserInfo> page = new Page<>(1, 5);
        this.userInfoService.page(page);
        return Result.ok(page);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public Result<UserInfo> getUserInfo() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USERNAME", user.getUsername());
        return Result.ok(this.userInfoService.getOne(queryWrapper));
    }


}

