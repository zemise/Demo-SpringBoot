package io.github.zemise.springbootdeploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//public class SpringbootDeployApplication extends SpringBootServletInitializer {
public class SpringbootDeployApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDeployApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(SpringbootDeployApplication.class);
//    }
}
