package com.myclass.elearning.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CategoryPostDto {
    @JsonIgnore
    private Integer id;
    private String title;
    private String icon;
}
