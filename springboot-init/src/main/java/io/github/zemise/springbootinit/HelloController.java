package io.github.zemise.springbootinit;

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
    @RequestMapping("/hello222")
    public static String hello(){
        return "Hello spring boot 222!";
    }
}
