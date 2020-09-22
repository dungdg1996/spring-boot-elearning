package com.myclass.elearning.controller.web;

import com.myclass.elearning.entity.Category;
import com.myclass.elearning.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        return "category/category-index";
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("category", new Category());
        return "category/category-add";
    }

    @PostMapping("/add")
    public String add(@Valid Category category, BindingResult result){
        if (result.hasErrors()){
            return "category/category-add";
        }
        categoryService.save(category);
        return "redirect:/category";
    }

    @GetMapping("/edit")
    public String edit(Integer id, ModelMap modelMap){
        modelMap.addAttribute("category", categoryService.findById(id));
        return "category/category-edit";
    }

    @PostMapping("edit")
    public String edit(@Valid Category category, BindingResult result){
        if (result.hasErrors()){
            return "category/category-edit";
        }
        categoryService.save(category);
        return "redirect:/category";
    }

    @GetMapping("/delete")
    public String delete(Integer id){
        if (id != null)
            categoryService.delete(id);
        return "redirect:/category";
    }
}
