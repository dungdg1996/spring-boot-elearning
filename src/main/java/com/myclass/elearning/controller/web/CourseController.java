package com.myclass.elearning.controller.web;

import com.myclass.elearning.dto.CoursePostDto;
import com.myclass.elearning.entity.Course;
import com.myclass.elearning.service.CategoryService;
import com.myclass.elearning.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    private final CategoryService categoryService;

    public CourseController(CourseService courseService, CategoryService categoryService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("course", courseService.findAll());
        return "course/course-index";
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        modelMap.addAttribute("course", new Course());
        return "course/course-add";
    }

    @PostMapping("/add")
    public String add(@Valid CoursePostDto course, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()){
            modelMap.addAttribute("categories", categoryService.findAll());
            return "course/course-add";
        }
        courseService.save(course);
        return "redirect:/course";
    }

    @GetMapping("/edit")
    public String edit(ModelMap modelMap, Integer id){
        modelMap.addAttribute("categories", categoryService.findAll());
        modelMap.addAttribute("course", courseService.get(id));
        return "course/course-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid Course course, BindingResult result, ModelMap modelMap){
        if (result.hasErrors()){
            modelMap.addAttribute("categories", categoryService.findAll());
            return "course/course-edit";
        }
        courseService.save(course);
        return "redirect:/course";
    }

    @GetMapping("/delete")
    public String delete(Integer id) {
        if (id != null) courseService.delete(id);
        return "redirect:/course";
    }
}
