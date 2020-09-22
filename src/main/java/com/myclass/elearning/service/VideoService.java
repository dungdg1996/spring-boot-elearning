package com.myclass.elearning.service;

import com.myclass.elearning.dto.VideoPostDto;
import com.myclass.elearning.dto.VideoPutDto;
import com.myclass.elearning.entity.Video;

import java.util.List;
import java.util.Optional;

public interface VideoService {
    List<Video> findAll();

    Optional<Video> findById(int id);

    void delete(Video Video);

    void save(Video Video);

    void save(VideoPostDto dto);

    void save(VideoPutDto dto);

    void delete(Integer id);
}
