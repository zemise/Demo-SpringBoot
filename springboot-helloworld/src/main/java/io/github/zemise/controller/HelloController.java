package io.github.zemise.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/9/5
 */

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello Spring Boot!";
    }
}
