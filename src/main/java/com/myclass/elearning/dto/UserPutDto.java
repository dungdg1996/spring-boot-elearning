package com.myclass.elearning.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserPutDto {
    @JsonIgnore
    private MultipartFile avatar;

    private Integer id;
    private String fullname;
    private String email;
    private String password;
    private String confirm;
    private String phone;
    private String address;
    private String facebook;
    private String website;
    private int roleId;

}
