package com.myclass.elearning.service;

import com.myclass.elearning.dto.UserChangePasswordDto;
import com.myclass.elearning.dto.UserGetDto;
import com.myclass.elearning.dto.UserPostDto;
import com.myclass.elearning.dto.UserPutDto;
import com.myclass.elearning.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int id);

    User save(User user);

    void save(UserPostDto dto);

    void save(UserPutDto user);

    void delete(Integer id);

    void changePassword(UserChangePasswordDto passwordDto);
}
