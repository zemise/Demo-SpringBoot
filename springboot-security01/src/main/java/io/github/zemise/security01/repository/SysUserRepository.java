package io.github.zemise.security01.repository;

import io.github.zemise.security01.domain.SysRole;
import io.github.zemise.security01.domain.SysUser;
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
     * @param username
     * @return SysUser
     */
    SysUser findByUsername(String username);

    /**
     * 通过用户名username查找其具有的权限列表
     *
     * @param username
     * @return List<SysRole><
     */
    @Query(value = "select new io.github.zemise.security01.domain.SysRole(sr.id, sr.name)\n" +
            " from SysUser  as su\n" +
            " join su.roles as sr\n" +
            " where su.username =?1")
    List<SysRole> findRolesByUsername(String username);

}
