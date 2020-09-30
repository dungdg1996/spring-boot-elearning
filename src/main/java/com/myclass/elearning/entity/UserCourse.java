package com.myclass.elearning.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_courses")
@Getter
@Setter
public class UserCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "course_id")
    private int courseId;
}
