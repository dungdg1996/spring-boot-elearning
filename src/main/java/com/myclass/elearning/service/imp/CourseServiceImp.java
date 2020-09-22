package com.myclass.elearning.service.imp;

import com.myclass.elearning.dto.CourseGetDto;
import com.myclass.elearning.dto.CoursePostDto;
import com.myclass.elearning.dto.CoursePutDto;
import com.myclass.elearning.entity.Course;
import com.myclass.elearning.exception.CourseNotFoundException;
import com.myclass.elearning.repo.CourseRepo;
import com.myclass.elearning.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class CourseServiceImp implements CourseService {
    private final CourseRepo courseRepo;
    private final ModelMapper modelMapper;

    public CourseServiceImp(CourseRepo courseRepo, ModelMapper modelMapper) {
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CourseGetDto> findAll() {
        return courseRepo.findAll().stream()
                .map(course -> modelMapper.map(course, CourseGetDto.class))
                .collect(toList());
    }

    @Override
    public List<CourseGetDto> findByCategoryId(int id) {
        return courseRepo.findAllByCategoryId(id).stream()
                .map(course -> modelMapper.map(course, CourseGetDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseGetDto> findByUserId(int userId) {
        return null;
    }

    @Override
    public CourseGetDto get(int id) {
        Course course = courseRepo.findById(id).orElseThrow(CourseNotFoundException::new);
        return modelMapper.map(course, CourseGetDto.class);
    }

    @Override
    public void delete(Integer id) {
        courseRepo.findById(id).ifPresent(courseRepo::delete);
    }

    @Override
    public void save(CoursePostDto coursePostDto) {
        Course course = modelMapper.map(coursePostDto, Course.class);
        course.setPromotionPrice(course.getPrice()*course.getDiscount() / 100);
        long time = new java.util.Date().getTime();
        course.setLastUpdate(new Date(time));
        courseRepo.save(course);
    }

    @Override
    public void save(CoursePutDto coursePutDto) {
        courseRepo.findById(coursePutDto.getId()).orElseThrow(CourseNotFoundException::new);
        Course course = modelMapper.map(coursePutDto, Course.class);
        course.setLastUpdate(new Date(new java.util.Date().getTime()));
        course.setPromotionPrice(course.getPrice()*course.getDiscount() / 100);
        courseRepo.save(course);
    }

    @Override
    public void save(Course course) {
        courseRepo.save(course);
    }
}
