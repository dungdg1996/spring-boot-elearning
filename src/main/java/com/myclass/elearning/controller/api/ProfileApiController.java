package com.myclass.elearning.controller.api;

import com.myclass.elearning.dto.UserGetDto;
import com.myclass.elearning.service.CourseService;
import com.myclass.elearning.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/my-profile")
public class ProfileApiController {
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CourseService courseService;

    public ProfileApiController(ModelMapper modelMapper, UserService userService, CourseService courseService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping
    public Object myProfile(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();
            return modelMapper.map(userService.findByEmail(email), UserGetDto.class);
        } catch (Exception e){
            throw new RuntimeException("Lỗi, phiên đăng nhập hết hạn. Hãy đăng nhập lại");
        }
    }

    @GetMapping("/courses")
    public Object myCourse(){
        try {
            String email =((UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal()).getUsername();
            return courseService.findByUserEmail(email);
        } catch (Exception e){
            throw new RuntimeException("Lỗi, phiên đăng nhập hết hạn. Hãy đăng nhập lại");
        }
    }

}
