package com.myclass.elearning.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value = {"home", ""})
    public String index(){
        return "redirect:/user";
    }
}
