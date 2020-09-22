package com.myclass.elearning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @JoinColumn(name = "course_id")
    private int courseId;
}
