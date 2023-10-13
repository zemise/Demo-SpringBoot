package io.github.zemise.security01;

import io.github.zemise.security01.jpa.domain.SysRole;
import io.github.zemise.security01.jpa.repository.SysUserRepository;
import io.github.zemise.security01.jpa.service.MyCustomUserService;
import io.github.zemise.security01.jpa.service.SysService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class SpringbootSecurity01ApplicationTests {
    @Resource
    private SysUserRepository userRepository;

    @Resource
    private SysService service;

    @Resource
    private MyCustomUserService myCustomUserService;

    @Transactional(readOnly = true)
    @Test
    void test(){
        System.out.println(userRepository.findByUsername("admin"));
    }

    @Transactional(readOnly = true)
    @Test
    void testFind(){
//        System.out.println(service.findRolesByUsername("admin"));
        List<SysRole> roles = service.findRolesByUsername("admin");
        for (SysRole role : roles) {
            System.out.println(role.getPermissions());
        }

        System.out.println("==========");
        System.out.println(service.findPermissionsByUsername("admin"));

        System.out.println(service.findAllPermission());

        System.out.println(service.findAllRolePermission());
    }

    @Test
    @Transactional
    void testLoad(){
        myCustomUserService.loadUserByUsername("admin");
    }
}
