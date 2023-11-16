package io.github.zemise.thymeleaf.controller;

import io.github.zemise.thymeleaf.domain.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">zemise</a>
 * @Date 2023/11/11
 * @since 1.0
 */

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String showIndexPage(Model model){
        model.addAttribute("title", "传递的title");
        model.addAttribute("description", "传递的description");
        model.addAttribute("keywords", "传递的keywords");
        return "index";
    }

    @RequestMapping("/basicTrain")
    public String showBasciTrain(Model model){
        UserVo user = new UserVo();
        user.setUsername("zemise");
        user.setAge(88);
        user.setSex(1);
        user.setIsVip(true);
        user.setCreatedTime(new Date());
        user.setTags(Arrays.asList("swiftUI", "java", "R"));
        model.addAttribute("user", user);
        return "basic";
    }
}
