package io.github.zemise.config;

import io.github.zemise.domain.Role;
import io.github.zemise.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/9/8
 */

@Configuration
public class UserConfig {
    @Bean
    public User user(){
        return new User();
    }

    @Bean
    public Role role(){
        return new Role();
    }
}
