package com.myclass.elearning.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"", "home", "index"})
    public String index() {
        return "redirect:/admin/course";
    }
}
