package com.myclass.elearning.controller.admin;

import com.myclass.elearning.dto.LoginDto;
import com.myclass.elearning.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AuthController {
    private final UserService userService;
    private AuthenticationManager authenticationManager;

    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String index(){
        return "login/login";
    }

    @GetMapping("/login/error")
    public String error(ModelMap modelMap){
        modelMap.addAttribute("message", "Tên đăng nhập hoặc mật khẩu sai");
        return "login/login";
    }

    @GetMapping("/logout")
    public String logout(){
        SecurityContextHolder.clearContext();
        return "login/login";
    }
//    @PostMapping("/login")
//    public String login(@ModelAttribute LoginDto loginDto, ModelMap modelMap, HttpServletRequest request){
//        try {
//            UsernamePasswordAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
//            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
//            SecurityContextHolder.getContext().setAuthentication(authenticate);
//            request.getSession(true);
//            return "redirect:/admin/role";
//        } catch (Exception e) {
//            System.out.println("Đăng nhập thất bại");
//            modelMap.addAttribute("message", "Tên đăng nhập hoặc mật khẩu sai");
//            modelMap.addAttribute("dto", loginDto);
//            return "login/login";
//        }
//    }
}
