package com.myclass.elearning.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class RolePutDto {
    private Integer id;
    private String name;
    private String description;
}
