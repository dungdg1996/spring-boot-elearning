package com.myclass.elearning.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String index(){
        return "login/login";
    }
    @PostMapping
    public String login(){
        return "redirect:/home";
    }
}
