package com.myclass.elearning.controller.api;

import com.myclass.elearning.entity.Role;
import com.myclass.elearning.exception.RoleNotFoundException;
import com.myclass.elearning.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/roles")
public class RoleApiController {
    private final RoleService roleService;

    public RoleApiController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public Object all() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Object one(@PathVariable int id) {
        return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public Object add(@RequestBody @Valid Role role) {
        try {
            roleService.save(role);
            return new ResponseEntity<>(role, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Lưu quyền không thành công", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public Object update(@RequestBody Role role) {
        try {
            roleService.save(role);
            return new ResponseEntity<>("Lưu quyền thành công", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Lưu quyền không thành công", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable int id) throws RoleNotFoundException {
        try {
            roleService.delete(id);
            return new ResponseEntity<>("Xóa quyền thành công", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Xóa không thành công", HttpStatus.BAD_REQUEST);
        }
    }
}
