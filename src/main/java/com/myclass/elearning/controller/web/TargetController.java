package com.myclass.elearning.controller.web;

import com.myclass.elearning.entity.Target;
import com.myclass.elearning.service.CourseService;
import com.myclass.elearning.service.TargetService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/target")
public class TargetController {
    private final CourseService courseService;
    private final TargetService targetService;

    public TargetController(CourseService courseService, TargetService targetService) {
        this.courseService = courseService;
        this.targetService = targetService;
    }

    @GetMapping
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("targets", targetService.findAll());
        return "target/target-index";
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("courses", courseService.findAll());
        modelMap.addAttribute("target", new Target());
        return "target/target-add";
    }

    @PostMapping("/add")
    public String add(@Valid Target target, BindingResult result, ModelMap modelMap){
        if (result.hasErrors()){
            modelMap.addAttribute("courses", courseService.findAll());
            return "target/target-add";
        }
        targetService.save(target);
        return "redirect:/target";
    }

    @GetMapping("/edit")
    public String edit(Integer id, ModelMap modelMap){
        modelMap.addAttribute("target", targetService.findById(id));
        modelMap.addAttribute("courses", courseService.findAll());
        return "target/target-edit";
    }

    @PostMapping("edit")
    public String edit(@Valid Target target, BindingResult result, ModelMap modelMap){
        if (result.hasErrors()) {
            modelMap.addAttribute("courses", courseService.findAll());
            return "target/target-edit";
        }
        targetService.save(target);
        return "redirect:/target";
    }

    @GetMapping("/delete")
    public String delete(Integer id){
        targetService.delete(id);
        return "redirect:/target";
    }
}
