package com.myclass.elearning.service;

import com.myclass.elearning.dto.RoleGetDto;
import com.myclass.elearning.dto.RolePostDto;
import com.myclass.elearning.dto.RolePutDto;
import com.myclass.elearning.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();

    Role findById(Integer id);

    RoleGetDto getDto(Integer id);

    List<RoleGetDto> getAllDto();


    void delete(Integer id);

    void save(Role role);

    void save(RolePostDto postDto);

    void save(RolePutDto putDto);
}
