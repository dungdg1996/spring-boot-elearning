package com.myclass.elearning.controller.admin;

import com.myclass.elearning.dto.UserGetDto;
import com.myclass.elearning.dto.UserPostDto;
import com.myclass.elearning.entity.User;
import com.myclass.elearning.service.RoleService;
import com.myclass.elearning.service.StorageService;
import com.myclass.elearning.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final StorageService storageService;

    public UserController(UserService userService, RoleService roleService,
                          ModelMapper modelMapper, StorageService storageService) {
        this.userService = userService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.storageService = storageService;
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

    @GetMapping("/profile")
    public String profile(ModelMap modelMap){
        org.springframework.security.core.userdetails.UserDetails userDetails = (org.springframework.security.core.userdetails.UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername());
        modelMap.addAttribute("user", modelMapper.map(user, UserPostDto.class));
        modelMap.addAttribute("roles", roleService.findAll());
        return "user/user-edit";
    }

    @PostMapping("/add")
    public String add(UserPostDto dto) {
        User user = modelMapper.map(dto, User.class);
        MultipartFile file = dto.getAvatar();
        System.out.println(dto);
        System.out.println(file.getOriginalFilename());
        if (!dto.getAvatar().isEmpty())
            user.setAvatar( storageService.store(file));
        userService.save(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Integer id, ModelMap modelMap){
        User user = userService.findById(id);
        modelMap.addAttribute("user", modelMapper.map(user, UserPostDto.class));
        modelMap.addAttribute("roles", roleService.findAll());
        return "user/user-edit";
    }

    @PostMapping("/edit")
    public String edit(UserPostDto dto) {
        User user = userService.findById(dto.getId());
        user.setEmail(dto.getEmail());
        user.setFullname(dto.getFullname());
        user.setAddress(dto.getAddress());
        user.setPhone(dto.getPhone());
        user.setRoleId(dto.getRoleId());
        user.setAvatar(storageService.store(dto.getAvatar()));
        userService.save(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/delete")
    public String delete(Integer id) {
        userService.delete(id);
        return "redirect:/admin/user";
    }
}
