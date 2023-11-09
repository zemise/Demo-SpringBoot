package io.github.zemise.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LogDemo {
    @Bean
    public String logMethod(){
        // 从trace到error日志级别由低到高
        // 可以调整输出的日志级别，日志就只会在这个级别后的高级别生效
        log.trace("LogDemo trace日志...");
        log.debug("LogDemo debug日志...");

        // spring boot默认使用的是info级别，没有指定级别就用默认规定的级别，即root级别
        log.info("LogDemo info日志...");
        log.warn("LogDemo warn日志...");
        log.error("LogDemo error日志...");

        return "hello log";

    }
}
