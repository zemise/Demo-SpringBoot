package io.github.zemise.springbootevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootEventApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootEventApplication.class, args);
        ReleaseEvent bean = context.getBean(ReleaseEvent.class);
        bean.publishUserLogged();

    }

}
