package com.myclass.elearning.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class RolePostDto {
    @JsonIgnore
    private Integer id;

    private String name;
    private String description;
}
