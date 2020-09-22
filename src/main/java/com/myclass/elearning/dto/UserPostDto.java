package com.myclass.elearning.dto;

import lombok.Data;

@Data
public class UserPostDto {
    private String fullname;
    private String email;
    private String password;
    private String confirm;
    private String phone;
    private String address;
    private String avatar;
    private int roleId;
}
