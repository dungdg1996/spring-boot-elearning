package com.myclass.elearning.controller.web;

import com.myclass.elearning.entity.Role;
import com.myclass.elearning.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String index() {
        return "role/role-index";
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute(new Role());
        return "role/role-add";
    }

    @PostMapping("add")
    public String add(Role role){
        roleService.save(role);
        return "redirect:/role";
    }
}
