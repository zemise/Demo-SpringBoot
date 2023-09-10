package io.github.zemise.springbootactuator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/9/11
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/findAll")
    public String findAll(){
        return "success";
    }
}
