package com.myclass.elearning.dto;

import com.myclass.elearning.entity.Course;
import com.myclass.elearning.entity.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserGetDto {
    private Integer id;
    private String email;
    private String fullname;
    private String phone;
    private String address;
    private String avatar;
    private String facebook;
    private String website;

    private Role role;
    private Set<Course> courses;
}
