package com.myclass.elearning.service.imp;

import com.myclass.elearning.dto.VideoGetDto;
import com.myclass.elearning.dto.VideoPostDto;
import com.myclass.elearning.dto.VideoPutDto;
import com.myclass.elearning.entity.Video;
import com.myclass.elearning.repo.VideoRepo;
import com.myclass.elearning.service.StorageService;
import com.myclass.elearning.service.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackOn = Exception.class)
public class VideoServiceImp implements VideoService {
    private final VideoRepo videoRepo;
    private final ModelMapper modelMapper;
    private final StorageService storageService;

    public VideoServiceImp(VideoRepo videoRepo, ModelMapper modelMapper, StorageService storageService) {
        this.videoRepo = videoRepo;
        this.modelMapper = modelMapper;
        this.storageService = storageService;
    }

    @Override
    public List<Video> findAll() {
        return videoRepo.findAll();
    }

    @Override
    public Video findById(Integer id) {
        return videoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy video với id = " + id));
    }

    @Override
    public VideoGetDto getDto(Integer id) {
        return modelMapper.map(findById(id), VideoGetDto.class);
    }

    @Override
    public List<VideoGetDto> getAllDto() {
        return videoRepo.findAll().stream()
                .map(video -> modelMapper.map(video, VideoGetDto.class))
                .collect(Collectors.toList());
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
        if (!dto.getImage().isEmpty())
            video.setImage(storageService.store(dto.getImage()));
        videoRepo.save(video);
    }

    @Override
    public void save(VideoPutDto dto) {
        videoRepo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy video với id = " + dto.getImage()));
        Video video = modelMapper.map(dto, Video.class);
        videoRepo.save(video);
    }

    @Override
    public void delete(Integer id) {
        Video video = videoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy video với id = " + id));
        videoRepo.delete(video);
    }
}
