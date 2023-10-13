package io.github.zemise.security01.jpa.service;

import io.github.zemise.security01.jpa.domain.SysRole;
import io.github.zemise.security01.jpa.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class MyCustomUserService implements UserDetailsService {
    private final SysService service;

    @Autowired
    public MyCustomUserService(SysService service) {
        this.service = service;
    }

    /**
     * 通过用户名获取用户的基本信息和权限
     * @param username the username identifying the user whose data is required.
     * @return userDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("根据名称获取用户信息：username is {}", username);
        SysUser user = service.findByUsername(username);
        Optional.ofNullable(user).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Not user found with username '%s'", username))
        );

        // 获取所有请求的URL
        List<SysRole> roles = service.findRolesByUsername(user.getUsername());
        log.info("用户角色个数为{}", roles.size());

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        // 封装用户信息和角色信息到Security ContextHolder全局缓存中
        roles.forEach(role -> {
            log.info("name---->{}", role.getName());
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new User(user.getUsername(),user.getPassword(),grantedAuthorities);
    }
}
