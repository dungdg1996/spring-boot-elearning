package com.myclass.elearning.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Tiêu đề video không được trống")
    private String title;
    @NotBlank(message = "Url video không được trống")
    private String url;
    @Min(value = 5, message = "Thời lượng video phải có ít nhất 5 phút")
    private int timeCount;
    @NotBlank(message = "Bạn chưa tải ảnh cho video")
    private String image;

    @Column(name = "course_id")
    private int courseId;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id",
            updatable = false, insertable = false)
    private Course course;
}
