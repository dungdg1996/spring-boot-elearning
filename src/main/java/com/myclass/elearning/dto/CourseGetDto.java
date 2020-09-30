package com.myclass.elearning.dto;

import com.myclass.elearning.entity.Category;
import com.myclass.elearning.entity.Target;
import com.myclass.elearning.entity.Video;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Data
public class CourseGetDto {
    private Integer id;
    private String title;
    private String image;
    private int lecturesCount;
    private int hourCount;
    private int viewCount;
    private double price;
    private int discount;
    private double promotionPrice;
    private String description;
    private String content;
    private Date lastUpdate;

    private Category category;
    private Set<Target> targets;
    private Set<Video> videos;
}
