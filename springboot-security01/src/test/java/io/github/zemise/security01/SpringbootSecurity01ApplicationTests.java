package io.github.zemise.security01;

import io.github.zemise.security01.domain.SysRole;
import io.github.zemise.security01.domain.SysUser;
import io.github.zemise.security01.repository.SysUserRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class SpringbootSecurity01ApplicationTests {
    @Resource
    private SysUserRepository userRepository;

    @Test
    void testFindByUsername(){
        SysUser user = userRepository.findByUsername("admin");
        Assert.notNull(user, "User 'admin' should exit");
        System.out.println(user.getPassword());
    }

    @Test
    void testFindRolesByUsername(){
        List<SysRole> list = userRepository.findRolesByUsername("admin");
        System.out.println(list);


    }
}
