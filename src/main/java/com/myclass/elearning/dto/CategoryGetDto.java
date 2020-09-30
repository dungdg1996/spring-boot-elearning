package com.myclass.elearning.dto;

import com.myclass.elearning.entity.Course;
import lombok.Data;

import java.util.Set;

@Data
public class CategoryGetDto {
    private Integer id;
    private String title;
    private String icon;

    private Set<Course> courses;
}
