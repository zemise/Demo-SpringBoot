package io.github.zemise.security01.jpa.config;

import io.github.zemise.security01.jpa.service.MyCustomUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@ComponentScan("io.github.zemise.security01")
@Slf4j
public class WebSecurityConfig{
    private MyCustomUserService myCustomUserService;

    // 通过实现UserDetailService来进行验证
    @Autowired
    public WebSecurityConfig(MyCustomUserService myCustomUserService) {
        this.myCustomUserService = myCustomUserService;
    }


    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
                        .requestMatchers("/", "index", "/login", "/css/**", "/js/**").permitAll()
                                .anyRequest().authenticated()
//                        .requestMatchers("/resources/**").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/content/**").hasRole("USER")
                )
                .formLogin(form -> form
                        // 自定义登陆界面
                        .loginPage("/login")
                        .successForwardUrl("/hello")
                        .permitAll()
                )
                .logout(logout -> logout
                        .deleteCookies("remove")
                        .invalidateHttpSession(false)
                        //.logoutUrl("/custom-logout")
                        .permitAll()
                )
                .csrf(cs -> cs
                        .ignoringRequestMatchers("/logout"));
        return http.build();
    }
}
