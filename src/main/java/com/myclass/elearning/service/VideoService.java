package com.myclass.elearning.service;

import com.myclass.elearning.dto.VideoGetDto;
import com.myclass.elearning.dto.VideoPostDto;
import com.myclass.elearning.dto.VideoPutDto;
import com.myclass.elearning.entity.Video;

import java.util.List;

public interface VideoService {
    List<Video> findAll();

    Video findById(Integer id);

    VideoGetDto getDto(Integer id);

    List<VideoGetDto> getAllDto();

    void delete(Video Video);

    void save(Video Video);

    void save(VideoPostDto dto);

    void save(VideoPutDto dto);

    void delete(Integer id);
}
