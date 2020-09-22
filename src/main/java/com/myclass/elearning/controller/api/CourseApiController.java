package com.myclass.elearning.controller.api;

import com.myclass.elearning.dto.CoursePostDto;
import com.myclass.elearning.dto.CoursePutDto;
import com.myclass.elearning.exception.CourseNotFoundException;
import com.myclass.elearning.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseApiController {
    private final CourseService courseService;

    public CourseApiController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public Object all() {
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Object one(@PathVariable int id) throws CourseNotFoundException {
        return new ResponseEntity<>(courseService.get(id), HttpStatus.OK);
    }

    @PostMapping
    public Object add(@RequestBody CoursePostDto course) {
        try {
            courseService.save(course);
            return new ResponseEntity<>("Lưu khóa học thành công", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Lưu khóa học thất bại : " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping
    public Object update(@RequestBody CoursePutDto course) {
        try {
            courseService.save(course);
            return new ResponseEntity<>("Lưu khóa học thành công", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Lưu khóa học thất bại : " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable int id) throws CourseNotFoundException {
        try {
            courseService.delete(id);
            return new ResponseEntity<>("Xóa khóa học thành công", HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>("Lưu khóa học thất bại : " + e.getMessage(),
                    HttpStatus.CREATED);
        }

    }
}
