package com.myclass.elearning.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class VideoPostDto {
    @JsonIgnore
    private Integer id;
    @JsonIgnore
    private MultipartFile image;

    private String title;
    private String url;
    private int timeCount;
    private int courseId;

}
