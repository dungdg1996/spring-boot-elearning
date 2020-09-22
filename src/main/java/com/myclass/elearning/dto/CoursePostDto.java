package com.myclass.elearning.dto;

import com.myclass.elearning.entity.Target;
import com.myclass.elearning.entity.Video;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Data
public class CoursePostDto {
    private String title;
    private String image;
    private int lecturesCount;
    private int hourCount;
    private int viewCount;
    private double price;
    private int discount;
    private String description;
    private String content;
    private int categoryId;
}
