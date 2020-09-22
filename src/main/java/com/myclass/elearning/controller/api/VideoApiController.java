package com.myclass.elearning.controller.api;

import com.myclass.elearning.dto.VideoPutDto;
import com.myclass.elearning.entity.Video;
import com.myclass.elearning.exception.VideoNotFoundException;
import com.myclass.elearning.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VideoApiController {
    private final VideoService videoService;

    public VideoApiController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/videos")
    public ResponseEntity<List<Video>> all() {
        return new ResponseEntity<>(videoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/videos/{id}")
    public ResponseEntity<Video> one(@PathVariable int id) throws VideoNotFoundException {
        return new ResponseEntity<>(videoService.findById(id)
                .orElseThrow(VideoNotFoundException::new),
                HttpStatus.OK);
    }

    @PostMapping("/videos")
    public Object add(@RequestBody Video video) {
        videoService.save(video);
        return new ResponseEntity<>(video, HttpStatus.CREATED);
    }

    @PutMapping("/videos/{id}")
    public Object update(@RequestBody VideoPutDto video, @PathVariable int id) {
        videoService.save(video);
        return new ResponseEntity<>("Lưu video thành công", HttpStatus.OK);
    }

    @DeleteMapping("/videos/{id}")
    public Object delete(@PathVariable int id) throws VideoNotFoundException {
        Video video = videoService.findById(id)
                .orElseThrow(VideoNotFoundException::new);
        videoService.delete(video);
        return new ResponseEntity<>(video, HttpStatus.OK);
    }
}
