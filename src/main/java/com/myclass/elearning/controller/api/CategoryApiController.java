package com.myclass.elearning.controller.api;

import com.myclass.elearning.dto.CategoryPostDto;
import com.myclass.elearning.dto.CategoryPutDto;
import com.myclass.elearning.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryApiController {
    private final CategoryService categoryService;

    public CategoryApiController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Object all() {
        return ResponseEntity.ok()
                .body(categoryService.getAllDto());
    }

    @GetMapping("/{id}")
    public Object one(@PathVariable int id) {
        return ResponseEntity.ok()
                .body(categoryService.findById(id));
    }

    @GetMapping("/{id}/details")
    public Object details(@PathVariable int id) {
        return  ResponseEntity.ok()
                .body(categoryService.getDto(id));
    }

    @PostMapping
    public Object add(@RequestBody CategoryPostDto dto) {
        categoryService.save(dto);
        return new ResponseEntity<>("Thêm mới danh mục thành công", HttpStatus.CREATED);
    }

    @PutMapping
    public Object update(@RequestBody CategoryPutDto dto) {
        try {
            categoryService.save(dto);
            return new ResponseEntity<>("Lưu danh mục thành công", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Lưu danh mục thất bại " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable int id) {
        try {
            categoryService.delete(id);
            return new ResponseEntity<>("Xóa danh mục thành công", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Xóa danh mục thất bại : " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }

    }
}
