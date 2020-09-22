package com.myclass.elearning.dto;

import lombok.Data;

@Data
public class VideoPostDto {
    private String title;
    private String url;
    private int timeCount;
    private int courseId;
    private String image;
}
