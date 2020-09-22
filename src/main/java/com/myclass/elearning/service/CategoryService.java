package com.myclass.elearning.service;

import com.myclass.elearning.dto.CategoryGetDto;
import com.myclass.elearning.dto.CategoryPostDto;
import com.myclass.elearning.dto.CategoryPutDto;
import com.myclass.elearning.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryGetDto> findAll();

    Category findById(int id);

    CategoryGetDto get(int id);

    void delete(Integer id);

    void save(Category category);

    void save(CategoryPostDto postDto);

    void save(CategoryPutDto putDto);
}
