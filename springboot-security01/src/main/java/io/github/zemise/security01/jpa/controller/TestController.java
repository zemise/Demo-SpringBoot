package io.github.zemise.security01.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
    @GetMapping(value = "/")
    public String index(){return "index";}

    @GetMapping(value = "hello")
    public String hello(){
        return "hello";
    }

    @GetMapping(value = "login")
    public String login(){
        return "login";
    }

    @GetMapping(value = "admin")
    public String admin(Model model){
        model.addAttribute("extraInfo","你是admin");
        return "admin";
    }
}
