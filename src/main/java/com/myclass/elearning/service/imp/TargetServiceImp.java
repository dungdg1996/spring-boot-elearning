package com.myclass.elearning.service.imp;

import com.myclass.elearning.dto.TargetPostDto;
import com.myclass.elearning.entity.Target;
import com.myclass.elearning.repo.TargetRepo;
import com.myclass.elearning.service.TargetService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class TargetServiceImp implements TargetService {
    private final TargetRepo targetRepo;
    private final ModelMapper modelMapper;

    public TargetServiceImp(TargetRepo targetRepo, ModelMapper modelMapper) {
        this.targetRepo = targetRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Target> findAll() {
        return targetRepo.findAll();
    }

    @Override
    public Target findById(int id) {
        return targetRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy mục tiêu với id = " + id));
    }

    @Override
    public void delete(Integer id) {
        Target target = findById(id);
        targetRepo.delete(target);
    }

    @Override
    public Target save(Target target) {
        return targetRepo.save(target);
    }

    @Override
    public void save(TargetPostDto dto) {
        Target target = modelMapper.map(dto, Target.class);
        targetRepo.save(target);
    }

}
