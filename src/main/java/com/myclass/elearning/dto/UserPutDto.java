package com.myclass.elearning.dto;

import lombok.Data;

@Data
public class UserPutDto {
    private Integer id;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    private int roleId;
}
