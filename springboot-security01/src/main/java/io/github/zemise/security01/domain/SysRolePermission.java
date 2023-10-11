package io.github.zemise.security01.domain;

import lombok.Data;

import java.io.Serializable;

// 角色权限实体类
@Data
public class SysRolePermission implements Serializable {
    private static final long serialValueUID = 1L;

    /**
     * 角色
     */
    private Long roleId;
    private String roleName;

    /**
     * 权限
     */
    private Long permissionId;
    private String url;

    public SysRolePermission(Long roleId, String roleName, Long permissionId, String url) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.permissionId = permissionId;
        this.url = url;
    }
}
