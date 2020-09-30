package com.myclass.elearning.service.imp;

import com.myclass.elearning.dto.CourseGetDto;
import com.myclass.elearning.dto.CoursePostDto;
import com.myclass.elearning.dto.CoursePutDto;
import com.myclass.elearning.entity.Course;
import com.myclass.elearning.repo.CourseRepo;
import com.myclass.elearning.service.CourseService;
import com.myclass.elearning.service.StorageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackOn = Exception.class)
public class CourseServiceImp implements CourseService {
    private final CourseRepo courseRepo;
    private final ModelMapper modelMapper;
    private final StorageService storageService;

    public CourseServiceImp(CourseRepo courseRepo, ModelMapper modelMapper, StorageService storageService) {
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
        this.storageService = storageService;
    }

    @Override
    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    @Override
    public Course findById(Integer id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khóa học có id = " + id));
    }

    @Override
    public List<CourseGetDto> search(Integer categoryId, String key) {
        return courseRepo.search(categoryId, key).stream()
                .map(course -> modelMapper.map(course, CourseGetDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseGetDto> findByUserEmail(String email) {
        return courseRepo.findByUser(email).stream()
                .map(course -> modelMapper.map(course, CourseGetDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseGetDto getDto(Integer id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khóa học với id = " + id));
        course.setViewCount(course.getViewCount() + 1);
        courseRepo.save(course);
        return modelMapper.map(course, CourseGetDto.class);
    }

    @Override
    public List<CourseGetDto> getAllDto() {
        return courseRepo.findAll().stream()
                .map(course -> modelMapper.map(course, CourseGetDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        courseRepo.findById(id).ifPresent(courseRepo::delete);
    }

    @Override
    public void save(CoursePostDto dto) {
        Course course = modelMapper.map(dto, Course.class);
        course.setPromotionPrice(course.getPrice() * course.getDiscount() / 100);
        long time = new java.util.Date().getTime();
        course.setLastUpdate(new Date(time));
        if (!dto.getImage().isEmpty())
            course.setImage(storageService.store(dto.getImage()));
        courseRepo.save(course);
    }

    @Override
    public void save(CoursePutDto coursePutDto) {
        courseRepo.findById(coursePutDto.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khóa học với id = " + coursePutDto.getId()));
        Course course = modelMapper.map(coursePutDto, Course.class);
        course.setLastUpdate(new Date(new java.util.Date().getTime()));
        course.setPromotionPrice(course.getPrice() * course.getDiscount() / 100);
        courseRepo.save(course);
    }

    @Override
    public void save(Course course) {
        course.setLastUpdate(new Date(new java.util.Date().getTime()));
        course.setPromotionPrice(course.getPrice() * course.getDiscount() / 100);
        courseRepo.save(course);
    }
}
