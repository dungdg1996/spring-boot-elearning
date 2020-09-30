package com.myclass.elearning.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CoursePutDto {
    private Integer id;
    private String title;
    private int lecturesCount;
    private int hourCount;
    private double price;
    private int discount;
    private String description;
    private String content;
    private int categoryId;

    private MultipartFile image;
}
