package com.myclass.elearning.service.imp;

import com.myclass.elearning.dto.VideoPostDto;
import com.myclass.elearning.dto.VideoPutDto;
import com.myclass.elearning.entity.Video;
import com.myclass.elearning.exception.VideoNotFoundException;
import com.myclass.elearning.repo.VideoRepo;
import com.myclass.elearning.service.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImp implements VideoService {
    private final VideoRepo videoRepo;
    private final ModelMapper modelMapper;

    public VideoServiceImp(VideoRepo videoRepo, ModelMapper modelMapper) {
        this.videoRepo = videoRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Video> findAll() {
        return videoRepo.findAll();
    }

    @Override
    public Optional<Video> findById(int id) {
        return videoRepo.findById(id);
    }

    @Override
    public void delete(Video video) {
        videoRepo.delete(video);
    }

    @Override
    public void save(Video video) {
        videoRepo.save(video);
    }

    @Override
    public void save(VideoPostDto dto) {
        Video video = modelMapper.map(dto, Video.class);
        videoRepo.save(video);
    }

    @Override
    public void save(VideoPutDto dto) {
        videoRepo.findById(dto.getId()).orElseThrow(VideoNotFoundException::new);
        Video video = modelMapper.map(dto, Video.class);
        videoRepo.save(video);
    }

    @Override
    public void delete(Integer id) {
        Video video = videoRepo.findById(id).orElseThrow(VideoNotFoundException::new);
        videoRepo.delete(video);
    }
}
