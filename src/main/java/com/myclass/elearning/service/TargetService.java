package com.myclass.elearning.service;

import com.myclass.elearning.dto.TargetPostDto;
import com.myclass.elearning.entity.Target;

import java.util.List;
import java.util.Optional;

public interface TargetService {
    List<Target> findAll();

    Target findById(int id);

    void delete(Integer id);

    Target save(Target Target);

    void save(TargetPostDto dto);
}
