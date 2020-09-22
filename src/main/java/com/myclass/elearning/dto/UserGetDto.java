package com.myclass.elearning.dto;

import com.myclass.elearning.entity.Role;
import lombok.Data;

@Data
public class UserGetDto {
    private Integer id;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    private int roleId;
}
