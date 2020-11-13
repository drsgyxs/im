package com.drsg.demo.v1.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author YXs
 * @since 2020-11-12
 */
@TableName("ROLE_INFO")
public class RoleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ROLE_ID")
    private Long roleId;

    @TableField("ROLE_NAME")
    private String roleName;

    @TableField("DESCRIPTION")
    private String description;


    public Long getRoleId() {
        return roleId;
    }

    public RoleInfo setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public RoleInfo setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RoleInfo setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "RoleInfo{" +
        "roleId=" + roleId +
        ", roleName=" + roleName +
        ", description=" + description +
        "}";
    }
}
