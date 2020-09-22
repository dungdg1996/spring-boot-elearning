package com.myclass.elearning.dto;

import lombok.Data;

@Data
public class UserChangePasswordDto {
    private Integer userId;
    private String odlPassword;
    private String newPassword;
}
