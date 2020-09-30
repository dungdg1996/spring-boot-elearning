package com.myclass.elearning.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String fullname;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String avatar;
}
