package com.drsg.demo.v1.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.beans.Transient;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 
 * </p>
 *
 * @author YXs
 * @since 2020-11-12
 */
@TableName(value = "USER_INFO", resultMap = "UserRoleResultMap")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("USER_ID")
    private Long userId;

    @TableField("USERNAME")
    private String username;

    @TableField("PASSWORD")
    @JsonIgnore
    private String password;

    @TableField("AVATAR_URL")
    private String avatarUrl;

    @TableField("SEX")
    private String sex;

    @TableField("SIGNATURE")
    private String signature;

    @TableField("ENABLED")
    private String enabled;

    @TableField("CREATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @TableField(exist = false)
    private Set<RoleInfo> roles;

    public Set<RoleInfo> getRoles() {
        return roles;
    }

    public UserInfo setRoles(Set<RoleInfo> roles) {
        this.roles = roles;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public UserInfo setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserInfo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserInfo setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public UserInfo setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public UserInfo setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getSignature() {
        return signature;
    }

    public UserInfo setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public String getEnabled() {
        return enabled;
    }

    public UserInfo setEnabled(String enabled) {
        this.enabled = enabled;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public UserInfo setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
        "userId=" + userId +
        ", username=" + username +
        ", password=" + password +
        ", avatarUrl=" + avatarUrl +
        ", sex=" + sex +
        ", signature=" + signature +
        ", enabled=" + enabled +
        ", createTime=" + createTime +
        "}";
    }
}
