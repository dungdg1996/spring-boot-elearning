package com.myclass.elearning.service;

import com.myclass.elearning.dto.TargetPostDto;
import com.myclass.elearning.entity.Target;

import java.util.List;

public interface TargetService {
    List<Target> findAll();

    Target findById(int id);

    void delete(Integer id);

    Target save(Target Target);

    void save(TargetPostDto dto);
}
