package io.github.zemise.security01.domain.manytomany.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@EnableJpaAuditing
public class JpaConfig {

    // AuditorAware 返回当前用户
    @Bean
    public AuditorAware<String> auditorAware(){
       return new AuditorAware(){
            @Override
            public Optional getCurrentAuditor() {
                // 当前用户 session 或redis等等
                // 此处测试，写死了
                return Optional.of("zemise");
            }
        };
    }
}
