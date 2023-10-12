package io.github.zemise.security01.repository;

import io.github.zemise.security01.domain.SysRolePermission;
import io.github.zemise.security01.domain.entity.SysPermission;
import io.github.zemise.security01.domain.entity.SysRole;
import io.github.zemise.security01.domain.entity.SysUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRepository extends CrudRepository<SysUser, Long> {

    /**
     * 通过username查找user
     * username是唯一前提
     *
     * @param username 要查询的用户名
     * @return SysUser 所查用户名对应的SysUser对象
     */
    SysUser findByUsername(String username);

    /**
     * 通过用户名username查找其具有的权限列表
     *
     * @param username 要查询的用户名
     * @return List<SysRole> 所查用户名具备的角色列表
     */
    @Query(value = "select new io.github.zemise.security01.domain.entity.SysRole(user.id, role.name)\n" +
            " from SysUser  as user\n" +
            " join user.roles as role\n" +
            " where user.username =?1")
    List<SysRole> findRolesByUsername(String username);


    @Query(value = " select * from sys_user u where  u.username= ?1", nativeQuery = true)
    List<SysUser> findByName(String username);

    //    @Query(value = "select sp.*\n" +
//            " from sys_user su\n" +
//            " left join sys_user_role sur on su.id = sur.sys_user_id\n" +
//            " left join sys_permission sp on srp.sys_permission_id = sp.id\n" +
//            " where su.username = ?1", nativeQuery = true)
//    List<SysPermission> findSysPermissionsByUsername(String username);



        /**
         * 通过用户名查找权限
         * @param username
         * @return List<SysPermission>
         */
        @Query(value=" select sp.*\n" +
                "        from sys_user su\n" +
                "        left join sys_user_role  sur on su.id = sur.sys_user_id\n" +
                "        left join sys_role_permission srp on sur.sys_role_id = srp.sys_role_id\n" +
                "        left join sys_permission sp on srp.sys_permission_id = sp.id\n" +
                "        where su.username = ?1",nativeQuery = true)
        List<SysPermission> findPermissionsByUsername(String username);

        @Query(value="select new io.github.zemise.security01.domain.SysRolePermission(sr.id,sr.name,sp.id,sp.url) \n" +
                "        from SysRole as sr\n"+
                "        join sr.permissions as sp")
        List<SysRolePermission> findAllRolePermissoin();
}
