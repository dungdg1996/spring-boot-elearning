package com.myclass.elearning.controller.api;

import com.myclass.elearning.entity.Target;
import com.myclass.elearning.service.TargetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/targets")
public class TargetApiController {
    private final TargetService targetService;

    public TargetApiController(TargetService targetService) {
        this.targetService = targetService;
    }

    @GetMapping
    public Object all() {
        return new ResponseEntity<>(targetService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Object one(@PathVariable int id)  {
        return new ResponseEntity<>(targetService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public Object add(@RequestBody Target target) {
        targetService.save(target);
        return new ResponseEntity<>(target, HttpStatus.CREATED);
    }

    @PutMapping
    public Object update(@RequestBody Target target, @PathVariable int id) {
        targetService.save(target);
        return new ResponseEntity<>("Thêm mới mục tiêu thành công", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable int id)  {
        targetService.delete(id);
        return new ResponseEntity<>("Xóa mục tiêu thành công", HttpStatus.OK);
    }
}
