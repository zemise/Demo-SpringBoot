package io.github.zemise.springbootcondition.config;

import io.github.zemise.springbootcondition.condition.ConditionOnClass;
import io.github.zemise.springbootcondition.domain.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
    //@Conditional(ClassCondition.class)
//    @ConditionOnClass("redis.clients.jedis.Jedis")
    @ConditionOnClass("com.alibaba.fastjson.JSON")
    public User user() {
        return new User();
    }

    @Bean
    @ConditionalOnProperty(name = "zemise", havingValue = "x")
    public User user2() {
        return new User();
    }
}
