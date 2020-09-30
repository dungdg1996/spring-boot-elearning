package com.myclass.elearning.dto;

import com.myclass.elearning.entity.Course;
import lombok.Data;

@Data
public class TargetGetDto {
    private Integer id;
    private String title;
    private Course course;
}
