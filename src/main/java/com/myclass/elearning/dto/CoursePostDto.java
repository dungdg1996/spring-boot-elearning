package com.myclass.elearning.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CoursePostDto {
    @JsonIgnore
    private Integer id;

    private String title;
    private int lecturesCount;
    private int hourCount;
    private int categoryId;
    private double price;
    private int discount;
    private String description;
    private String content;

    @JsonIgnore
    private MultipartFile image;
}
