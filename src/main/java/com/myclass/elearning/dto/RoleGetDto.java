package com.myclass.elearning.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class RoleGetDto {
    private Integer id;
    private String name;
    private String description;
}
