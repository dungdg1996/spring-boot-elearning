package com.myclass.elearning.controller.api;

import com.myclass.elearning.dto.VideoPutDto;
import com.myclass.elearning.entity.Course;
import com.myclass.elearning.entity.Video;
import com.myclass.elearning.service.StorageService;
import com.myclass.elearning.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VideoApiController {
    private final VideoService videoService;
    private final StorageService storageService;

    public VideoApiController(VideoService videoService, StorageService storageService) {
        this.videoService = videoService;
        this.storageService = storageService;
    }

    @GetMapping("/videos")
    public ResponseEntity<List<Video>> all() {
        return new ResponseEntity<>(videoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/videos/{id}")
    public ResponseEntity<Video> one(@PathVariable int id)  {
        return new ResponseEntity<>(videoService.findById(id),
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
    public Object delete(@PathVariable int id) {
        try{
            videoService.delete(id);
            return new ResponseEntity<>("Xóa thành công video", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Xóa video không thành công : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/{id}/image")
    public Object uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Integer id){
        String imageName = storageService.store(image);
        Video video = videoService.findById(id);
        if (!image.isEmpty()) video.setImage(storageService.store(image));
        videoService.save(video);
        return ResponseEntity.ok().body("Lưu image thành công");
    }
}
