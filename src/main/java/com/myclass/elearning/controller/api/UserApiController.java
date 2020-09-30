package com.myclass.elearning.controller.api;

import com.myclass.elearning.dto.UserPutDto;
import com.myclass.elearning.entity.User;
import com.myclass.elearning.service.StorageService;
import com.myclass.elearning.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class UserApiController {
    private final UserService userService;
    private final StorageService storageService;
    private final ModelMapper modelMapper;

    public UserApiController(UserService userService, StorageService storageService, ModelMapper modelMapper) {
        this.userService = userService;
        this.storageService = storageService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public Object all() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Object one(@PathVariable int id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public Object add(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>("Tạo user thành công", HttpStatus.CREATED);
    }

    @PutMapping
    public Object update(@RequestBody UserPutDto user) {
        userService.save(user);
        return new ResponseEntity<>("Cập nhật user thành công", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable int id) {
        userService.delete(id);
        return new ResponseEntity<>("Xóa user thành công", HttpStatus.OK);
    }

    @PostMapping("/{id}/avatar")
    public Object uploadAvatar(@PathVariable Integer id,
                               @RequestParam("file") MultipartFile file){
        User user = userService.findById(id);
        String avatar = storageService.store(file);
        user.setAvatar(avatar);
        userService.save(user);
        return ResponseEntity.ok().body("Lưu avatar thành công");
    }

    @PostMapping("/{userId}")
    public Object addCourse(@PathVariable Integer userId , Integer courseId){
        userService.addCourse(userId, courseId);
        return ResponseEntity.ok().body("Đăng ký khóa học thành công");
    }
}
