package io.github.zemise.springbootenable;

import io.github.zemise.config.MyImportBeanDefinitionRegistrar;
import io.github.zemise.config.MyImportSelector;
import io.github.zemise.domain.Role;
import io.github.zemise.domain.User;
import io.github.zemise.config.UserConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.Map;

/**
 * @ComponentScan 扫描范围：当前引导类所在的包及其子包
 * 本包为： io.github.zemise.springbootenable;
 * 其他子包为： io.github.zemise.domain.config;
 *
 * 如何解决呢？
 * 1. 使用@CompotentScan扫描io.github.zemise.domain.config包
 * 2. 可以@Import注解，加载类。这些类都会被Spring创建，并放入IOC容器
 * 3. 可以对Import注解进行封装
 */

/**
 * Import的4种用法
 * 1. 导入Bean
 * 2. 导入配置类
 * 3. 导入ImportSelector的实现类
 * 4. 导入ImportBeanDefinitionRegistrar实现类
 */
@SpringBootApplication
//@ComponentScan("io.github.zemise.domain.config")
//@Import(UserConfig.class)
//@EnableUser
//@Import(User.class)
//@Import(UserConfig.class)
//@Import(MyImportSelector.class)
@Import(MyImportBeanDefinitionRegistrar.class)
public class SpringbootEnableApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootEnableApplication.class, args);

        /*
        // 获取Bean
        Object user = context.getBean("user");
        System.out.println(user)
        ;*/

        /*
        User user = context.getBean(User.class);
        System.out.println(user);

        Role role = context.getBean(Role.class);
        System.out.println(role);
        */

        Object user = context.getBean("user");
        System.out.println(user);

        /*
        Map<String, User> map = context.getBeansOfType(User.class);
        System.out.println(map);*/
    }

}
