package com.myclass.elearning.service;

import com.myclass.elearning.dto.*;
import com.myclass.elearning.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Integer id);

    UserGetDto getDto(Integer id);

    List<UserGetDto> getAllDto();

    User save(User user);

    void save(UserPostDto dto);

    void addCourse(Integer userId, Integer courseId);

    void save(UserPutDto user);

    void save(UserChangePasswordDto passwordDto);

    void save(UserRegisterDto dto);

    void delete(Integer id);

    User findByEmail(String email);

}
