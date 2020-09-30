package com.myclass.elearning.service;

import com.myclass.elearning.dto.CategoryGetDto;
import com.myclass.elearning.dto.CategoryPostDto;
import com.myclass.elearning.dto.CategoryPutDto;
import com.myclass.elearning.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(int id);

    List<CategoryGetDto> getAllDto();

    CategoryGetDto getDto(int id);

    void delete(Integer id);

    void save(Category category);

    void save(CategoryPostDto postDto);

    void save(CategoryPutDto putDto);


}
