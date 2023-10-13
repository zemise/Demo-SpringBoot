package io.github.zemise.security01.jpa.repository;

import io.github.zemise.security01.jpa.domain.SysPermission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SysPermissionRepository extends CrudRepository<SysPermission, Long> {
}
