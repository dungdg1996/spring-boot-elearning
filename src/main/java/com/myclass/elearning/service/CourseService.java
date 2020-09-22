package com.myclass.elearning.service;

import com.myclass.elearning.dto.CourseGetDto;
import com.myclass.elearning.dto.CoursePostDto;
import com.myclass.elearning.dto.CoursePutDto;
import com.myclass.elearning.entity.Course;

import java.util.List;

public interface CourseService {
    List<CourseGetDto> findAll();

    List<CourseGetDto> findByCategoryId(int categoryId);

    List<CourseGetDto> findByUserId(int UserId);

    CourseGetDto get(int id);

    void delete(Integer Course);

    void save(Course course);

    void save(CoursePostDto Course);

    void save(CoursePutDto course);
}
