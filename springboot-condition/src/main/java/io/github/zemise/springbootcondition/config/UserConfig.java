package io.github.zemise.springbootcondition.config;

import io.github.zemise.springbootcondition.condition.ClassCondition;
import io.github.zemise.springbootcondition.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
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
    @Conditional(ClassCondition.class)
    public User user() {
        return new User();
    }
}
