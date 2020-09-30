package com.myclass.elearning.controller.admin;

import com.myclass.elearning.dto.VideoPostDto;
import com.myclass.elearning.entity.Video;
import com.myclass.elearning.service.CourseService;
import com.myclass.elearning.service.StorageService;
import com.myclass.elearning.service.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/video")
public class VideoController {
    private final CourseService courseService;
    private final VideoService videoService;
    private final StorageService storageService;
    private final ModelMapper modelMapper;

    public VideoController(CourseService courseService, VideoService videoService, StorageService storageService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.videoService = videoService;
        this.storageService = storageService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("videos", videoService.findAll());
        return "video/video-index";
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("video", new VideoPostDto());
        modelMap.addAttribute("courses", courseService.findAll());
        return "video/video-add";
    }

    @PostMapping("/add")
    public String add(@Valid VideoPostDto dto, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()){
            modelMap.addAttribute("courses", courseService.findAll());
            return "video/video-add";
        }
        videoService.save(dto);
        return "redirect:/admin/video";
    }

    @GetMapping("/edit")
    public String edit(Integer id, ModelMap modelMap){
        Video video = videoService.findById(id);
        modelMap.addAttribute("video",
                modelMapper.map(video, VideoPostDto.class));
        modelMap.addAttribute("courses", courseService.findAll());
        return "video/video-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid VideoPostDto dto,BindingResult result, ModelMap modelMap){
        if (result.hasErrors()){
            modelMap.addAttribute("courses", courseService.findAll());
            return "video/video-edit";
        }
        videoService.save(dto);
        return "redirect:/admin/video";
    }

    @GetMapping("/delete")
    public String delete(Integer id){
        videoService.delete(id);
        return "redirect:/admin/video";
    }
}
