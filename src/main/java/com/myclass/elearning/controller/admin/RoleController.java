package com.myclass.elearning.controller.admin;

import com.myclass.elearning.entity.Role;
import com.myclass.elearning.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("roles", roleService.findAll());
        return "role/role-index";
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute(new Role());
        return "role/role-add";
    }

    @GetMapping("/edit")
    public String edit(Integer id, ModelMap modelMap) {
        modelMap.addAttribute(roleService.findById(id));
        return "role/role-edit";
    }

    @GetMapping("/delete")
    public String delete(Integer id) {
        roleService.delete(id);
        return "redirect:/admin/role";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Role role){
        roleService.save(role);
        return "redirect:/admin/role";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Role role){
        roleService.save(role);
        return "redirect:/admin/role";
    }
}
