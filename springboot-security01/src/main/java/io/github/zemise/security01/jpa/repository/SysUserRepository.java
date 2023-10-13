package io.github.zemise.security01.jpa.repository;

import io.github.zemise.security01.jpa.domain.SysUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRepository extends CrudRepository<SysUser, Long> {
    SysUser findByUsername(String username);
}
