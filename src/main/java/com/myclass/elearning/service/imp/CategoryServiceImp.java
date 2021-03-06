package com.myclass.elearning.service.imp;

import com.myclass.elearning.dto.CategoryGetDto;
import com.myclass.elearning.dto.CategoryPostDto;
import com.myclass.elearning.dto.CategoryPutDto;
import com.myclass.elearning.entity.Category;
import com.myclass.elearning.repo.CategoryRepo;
import com.myclass.elearning.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackOn = Exception.class)
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    public CategoryServiceImp(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục với id = " + id));
    }

    @Override
    public List<CategoryGetDto> getAllDto() {
        return categoryRepo.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryGetDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryGetDto getDto(int id) {
        Category category = findById(id);
        return modelMapper.map(category, CategoryGetDto.class);
    }

    @Override
    public void delete(Integer id) {
        categoryRepo.findById(id).ifPresent(categoryRepo::delete);
    }

    @Override
    public void save(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public void save(CategoryPostDto dto) {
        Category category = modelMapper.map(dto, Category.class);
        categoryRepo.save(category);
    }

    @Override
    public void save(CategoryPutDto dto) {
        Category category = modelMapper.map(dto, Category.class);
        if (categoryRepo.findById(category.getId()).isPresent())
            categoryRepo.save(category);
    }

}
