package com.myclass.elearning.dto;

import com.myclass.elearning.entity.Course;
import lombok.Data;

@Data
public class VideoGetDto {
    private Integer id;
    private String title;
    private String url;
    private int timeCount;
    private String image;

    private Course course;
}
