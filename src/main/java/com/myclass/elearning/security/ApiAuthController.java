package com.myclass.elearning.security;

import com.myclass.elearning.dto.LoginDto;
import com.myclass.elearning.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class ApiAuthController {
    final AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;

    public ApiAuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setTokenProvider(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @PostMapping
    public Object login(@RequestBody LoginDto loginDto) {
        System.out.println(loginDto);
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
            // login method
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            // save authenticate to context
            SecurityContextHolder.getContext().setAuthentication(authenticate);

            String token = tokenProvider.creatToken(authenticate);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Sai thong tin dang nhap", HttpStatus.BAD_REQUEST);
        }
    }
}
