package com.myclass.elearning.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "targets")
public class Target {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Tiêu đề không được trống")
    private String title;

    @Column(name = "course_id")
    private int courseId;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id",
            updatable = false, insertable = false)
    private Course course;

}
