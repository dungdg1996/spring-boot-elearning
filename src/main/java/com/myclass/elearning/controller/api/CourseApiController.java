package com.myclass.elearning.controller.api;

import com.myclass.elearning.dto.CoursePostDto;
import com.myclass.elearning.dto.CoursePutDto;
import com.myclass.elearning.entity.Course;
import com.myclass.elearning.service.CourseService;
import com.myclass.elearning.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

@RestController
@RequestMapping("/api/courses")
public class CourseApiController {
    private final CourseService courseService;
    private final StorageService storageService;

    public CourseApiController(CourseService courseService, StorageService storageService) {
        this.courseService = courseService;
        this.storageService = storageService;
    }

    @GetMapping
    public Object all() {
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Object one(@PathVariable int id) {
        return ResponseEntity.ok()
                .body(courseService.findById(id));
    }

    @GetMapping("/{id}/details")
    public Object details(@PathVariable int id) {
        return ResponseEntity.ok().body(courseService.getDto(id));
    }

    @GetMapping("/_search")
    public Object search(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String key
    ) {
        try {
            return ResponseEntity.ok()
                    .body(courseService.search(categoryId, '%' + key + '%'));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
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
    public Object delete(@PathVariable int id) {
        try {
            courseService.delete(id);
            return new ResponseEntity<>("Xóa khóa học thành công", HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>("Lưu khóa học thất bại : " + e.getMessage(),
                    HttpStatus.CREATED);
        }

    }

    @PostMapping(value = "/{id}/image")
    public Object uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Integer id){
        String imageName = storageService.store(image);
        Course course = courseService.findById(id);
        course.setImage(imageName);
        courseService.save(course);
        return ResponseEntity.ok().body("Lưu ảnh thành công");
    }

}
