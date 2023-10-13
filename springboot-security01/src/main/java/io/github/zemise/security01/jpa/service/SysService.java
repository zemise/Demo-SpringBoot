package io.github.zemise.security01.jpa.service;

import io.github.zemise.security01.jpa.domain.SysPermission;
import io.github.zemise.security01.jpa.domain.SysRole;
import io.github.zemise.security01.jpa.domain.SysRolePermission;
import io.github.zemise.security01.jpa.domain.SysUser;
import io.github.zemise.security01.jpa.repository.SysRoleRepository;
import io.github.zemise.security01.jpa.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class SysService {
    private final SysUserRepository userRepository;
    private final SysRoleRepository roleRepository;

    @Autowired
    public SysService(SysUserRepository userRepository, SysRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public SysUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<SysRole> findRolesByUsername(String username) {
        SysUser user = userRepository.findByUsername(username);

        return user.getRoles();
    }

    public List<SysPermission> findPermissionsByUsername(String username) {
        SysUser user = userRepository.findByUsername(username);
        List<SysRole> roles = user.getRoles();
        List<SysPermission> permissionList = new ArrayList<>();
        for (SysRole role : roles) {
            permissionList.addAll(role.getPermissions());
        }
        return permissionList;
    }

    // 没太大必要的方法，这里只是想试试用HashSet去重，
    // 获得所有Permission，直接用PermissionRepository的findAll()就行

    /**
     * 根据SysRole表现有角色，获取所有Permission，去重
     *
     * @return List<SysPermission>
     */
    @Deprecated
    public List<SysPermission> findAllPermission() {
        List<SysPermission> permissionListWithDuplicated = new ArrayList<>();
        roleRepository.findAll().forEach(u -> permissionListWithDuplicated.addAll(u.getPermissions()));

        HashSet<SysPermission> permissionSet = new HashSet<>(permissionListWithDuplicated);

        return new ArrayList<>(permissionSet);
    }

    /**
     * 获得资源和角色对应的表，也就是角色权限中间表
     * @return List<SysRolePermission>
     */
    public List<SysRolePermission> findAllRolePermission() {
        List<SysRolePermission> rolePermissionList = new ArrayList<>();
        roleRepository.findAll().forEach(role -> {
            role.getPermissions().forEach(permission -> {
                rolePermissionList.add(
                        new SysRolePermission(
                                role.getId(),
                                role.getName(),
                                permission.getId(),
                                permission.getUrl()));
            });
        });
        return rolePermissionList;
    }
}
