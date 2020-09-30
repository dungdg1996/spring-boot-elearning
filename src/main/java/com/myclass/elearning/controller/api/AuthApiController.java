package com.myclass.elearning.controller.api;

import com.myclass.elearning.dto.LoginDto;
import com.myclass.elearning.dto.UserRegisterDto;
import com.myclass.elearning.entity.Message;
import com.myclass.elearning.entity.User;
import com.myclass.elearning.security.JwtTokenProvider;
import com.myclass.elearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthApiController {
    final AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;
    private final UserService userService;

    public AuthApiController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Autowired
    public void setTokenProvider(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginDto loginDto) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
            // login method
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            // save authenticate to context
            SecurityContextHolder.getContext().setAuthentication(authenticate);

            String token = tokenProvider.creatToken(authenticate);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Sai thông tin đăng nhập", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/logout")
    public Object login() {
        try {
            SecurityContextHolder.clearContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Bạn đã đăng xuất thành công", HttpStatus.OK);
    }

    @PostMapping("/register")
    public Object register(@RequestBody UserRegisterDto registerDto){
        userService.save(registerDto);
        return new ResponseEntity<>("Đăng ký tài khoản thành công", HttpStatus.CREATED);
    }
}
