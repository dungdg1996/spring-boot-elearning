package com.myclass.elearning.dto;

import lombok.Data;

@Data
public class UserChangePasswordDto {
    private String email;
    private String odlPassword;
    private String newPassword;
}
