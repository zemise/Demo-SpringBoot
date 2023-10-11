package io.github.zemise.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
public class SecurityConfig {
    //@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/resources/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/content/**").hasRole("USER")
                )
                .formLogin(form -> form
                        // 自定义登陆界面
                        .loginPage("/login")
                        .successForwardUrl("/content")
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

    //    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(antMatcher("/")).permitAll()
                        .requestMatchers(antMatcher("/resources/**")).permitAll()
                        .requestMatchers(antMatcher("/admin/**")).hasRole("ADMIN")
                        .requestMatchers(antMatcher("/content/**")).hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                                .loginPage("/login")
                                // 登陆成功都到指定URL
//                                .successForwardUrl("/content")
                                // 为了看到自定义的错误提示，注销了这个错误指向URL
//                        .failureUrl("/login-error")
                                .permitAll()
                )
                .csrf(cs -> cs
                        .ignoringRequestMatchers("/logout"));

        return http.build();
    }

    // 忽略某些URL请求，生产环境勿用
    //@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/*");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails admin = User
                .withUsername("zemise")
                .password(encoder.encode("zemise"))
                .roles("ADMIN", "USER")
                .build();

        UserDetails user = User
                .withUsername("user")
                .password(encoder.encode("user"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }
}
