package com.myclass.elearning.service;

import com.myclass.elearning.dto.CourseGetDto;
import com.myclass.elearning.dto.CoursePostDto;
import com.myclass.elearning.dto.CoursePutDto;
import com.myclass.elearning.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll();

    Course findById(Integer id);

    CourseGetDto getDto(Integer id);

    List<CourseGetDto> getAllDto();

    List<CourseGetDto> search(Integer categoryId, String key);

    List<CourseGetDto> findByUserEmail(String email);

    void delete(Integer Course);

    void save(Course course);

    void save(CoursePostDto Course);

    void save(CoursePutDto course);
}
