package com.myclass.elearning.dto;

import lombok.Data;

@Data
public class VideoGetDto {
    private Integer id;
    private String title;
    private String url;
    private int timeCount;
    private int courseId;
    private String image;
}
