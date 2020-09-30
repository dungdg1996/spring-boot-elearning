package com.myclass.elearning.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserPostDto {
    @JsonIgnore
    private Integer id;
    @JsonIgnore
    private MultipartFile avatar;
    @JsonIgnore
    private String confirm;
    @JsonIgnore
    private int roleId;

    private String fullname;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String facebook;
    private String website;

}
