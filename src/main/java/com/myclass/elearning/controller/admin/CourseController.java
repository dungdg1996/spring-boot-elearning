package com.myclass.elearning.controller.admin;

import com.myclass.elearning.dto.CoursePostDto;
import com.myclass.elearning.entity.Course;
import com.myclass.elearning.service.CategoryService;
import com.myclass.elearning.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/course")
public class CourseController {
    private final CourseService courseService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public CourseController(CourseService courseService, CategoryService categoryService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
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
        return "redirect:/admin/course";
    }

    @GetMapping("/edit")
    public String edit(ModelMap modelMap, Integer id){
        modelMap.addAttribute("categories", categoryService.findAll());
        modelMap.addAttribute("course", modelMapper.map(courseService.findById(id), CoursePostDto.class));
        return "course/course-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid CoursePostDto dto, BindingResult result, ModelMap modelMap){
        if (result.hasErrors()){
            modelMap.addAttribute("categories", categoryService.findAll());
            return "course/course-edit";
        }
        courseService.save(dto);
        return "redirect:/admin/course";
    }

    @GetMapping("/delete")
    public String delete(Integer id) {
        if (id != null) courseService.delete(id);
        return "redirect:/admin/course";
    }
}
