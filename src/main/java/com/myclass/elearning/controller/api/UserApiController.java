package com.myclass.elearning.controller.api;

import com.myclass.elearning.entity.User;
import com.myclass.elearning.exception.UserNotFoundException;
import com.myclass.elearning.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserApiController {
    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> all() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> one(@PathVariable int id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/users")
    public Object add(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>("Tạo user thành công", HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public Object update(@RequestBody User newUser, @PathVariable int id) {
        userService.save(newUser);
        return new ResponseEntity<>("cập nhật user thành công", HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public Object delete(@PathVariable int id) {
        userService.delete(id);
        return new ResponseEntity<>("Xóa user thành công", HttpStatus.OK);
    }
}
