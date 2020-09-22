package com.myclass.elearning.controller.api;

import com.myclass.elearning.dto.CategoryPostDto;
import com.myclass.elearning.dto.CategoryPutDto;
import com.myclass.elearning.exception.CategoryNotFoundException;
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
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Object one(@PathVariable int id) throws CategoryNotFoundException {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public Object add(@RequestBody CategoryPostDto dto) {
        try {
            categoryService.save(dto);
            return new ResponseEntity<>("Thêm mới danh mục thành công", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Thêm mới danh mục thất bại " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping
    public Object update(@RequestBody CategoryPutDto dto) {
        try {
            categoryService.save(dto);
            return new ResponseEntity<>("Lưu danh mục thành công", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Lưu danh mục thất bại " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable int id)  {
        try {
            categoryService.delete(id);
            return new ResponseEntity<>("Xóa danh mục thành công", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Xóa danh mục thất bại : " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }

    }
}
