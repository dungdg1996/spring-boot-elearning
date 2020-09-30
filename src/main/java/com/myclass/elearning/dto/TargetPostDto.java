package com.myclass.elearning.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class TargetPostDto {
    @JsonIgnore
    private Integer id;

    private int title;
    private int courseId;
}
