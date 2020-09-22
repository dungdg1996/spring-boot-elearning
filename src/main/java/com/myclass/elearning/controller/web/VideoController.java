package com.myclass.elearning.controller.web;

import com.myclass.elearning.entity.Video;
import com.myclass.elearning.service.CourseService;
import com.myclass.elearning.service.VideoService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/video")
public class VideoController {
    private final CourseService courseService;
    private final VideoService videoService;

    public VideoController(CourseService courseService, VideoService videoService) {
        this.courseService = courseService;
        this.videoService = videoService;
    }

    @GetMapping
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("videos", videoService.findAll());
        return "video/video-index";
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("video", new Video());
        modelMap.addAttribute("courses", courseService.findAll());
        return "video/video-add";
    }

    @PostMapping("/add")
    public String add(@Valid Video video, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()){
            modelMap.addAttribute("courses", courseService.findAll());
            return "video/video-add";
        }
        videoService.save(video);
        return "redirect:/video";
    }

    @GetMapping("/edit")
    public String edit(Integer id, ModelMap modelMap){
        modelMap.addAttribute("video", videoService.findById(id).orElse(new Video()));
        modelMap.addAttribute("courses", courseService.findAll());
        return "video/video-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid Video video,BindingResult result, ModelMap modelMap){
        if (result.hasErrors()){
            modelMap.addAttribute("courses", courseService.findAll());
            return "video/video-edit";
        }
        videoService.save(video);
        return "redirect:/video";
    }

    @GetMapping("/delete")
    public String delete(Integer id){
        videoService.delete(id);
        return "redirect:/video";
    }
}
