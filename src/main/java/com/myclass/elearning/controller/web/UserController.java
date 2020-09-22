package com.myclass.elearning.controller.web;

import com.myclass.elearning.dto.UserGetDto;
import com.myclass.elearning.dto.UserPostDto;
import com.myclass.elearning.dto.UserPutDto;
import com.myclass.elearning.entity.User;
import com.myclass.elearning.exception.UserNotFoundException;
import com.myclass.elearning.service.RoleService;
import com.myclass.elearning.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, RoleService roleService, ModelMapper modelMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.findAll());
        return "user/user-index";
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserPostDto());
        modelMap.addAttribute("roles", roleService.findAll());
        return "user/user-add";
    }

    @PostMapping("/add")
    public String add(UserPostDto dto) {
        User user = modelMapper.map(dto, User.class);
        System.out.println(user);
        userService.save(user);
        return "redirect:/user";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Integer id, ModelMap modelMap){
        User user = userService.findById(id);
        modelMap.addAttribute("user", modelMapper.map(user, UserGetDto.class));
        modelMap.addAttribute("roles", roleService.findAll());
        return "user/user-edit";
    }

    @PostMapping("/edit")
    public String edit(UserPutDto user) {
        userService.save(user);
        return "redirect:/user";
    }

    @GetMapping("/delete")
    public String delete(Integer id) {
        userService.delete(id);
        return "redirect:/user";
    }
}
