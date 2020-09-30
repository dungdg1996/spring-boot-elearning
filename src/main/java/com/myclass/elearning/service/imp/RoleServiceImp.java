package com.myclass.elearning.service.imp;

import com.myclass.elearning.dto.RoleGetDto;
import com.myclass.elearning.dto.RolePostDto;
import com.myclass.elearning.dto.RolePutDto;
import com.myclass.elearning.entity.Role;
import com.myclass.elearning.repo.RoleRepo;
import com.myclass.elearning.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackOn = Exception.class)
public class RoleServiceImp implements RoleService {
    private final RoleRepo roleRepo;
    private final ModelMapper modelMapper;

    public RoleServiceImp(RoleRepo roleRepo, ModelMapper modelMapper) {
        this.roleRepo = roleRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    @Override
    public RoleGetDto getDto(Integer id) {
        return modelMapper.map(findById(id), RoleGetDto.class);
    }

    @Override
    public List<RoleGetDto> getAllDto() {
        return roleRepo.findAll().stream()
                .map(role -> modelMapper.map(role, RoleGetDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Role findById(Integer id) {
        return roleRepo.findById(id)
                .orElseThrow(() ->  new RuntimeException("Không tìm thấy khóa học có id = " + id));
    }

    @Override
    public void delete(Integer id) {
        Role role = findById(id);
        roleRepo.delete(role);
    }

    @Override
    public void save(Role role) {
        roleRepo.save(role);
    }

    @Override
    public void save(RolePostDto postDto) {
        Role role = modelMapper.map(postDto, Role.class);
        roleRepo.save(role);
    }

    @Override
    public void save(RolePutDto dto) {
        findById(dto.getId());
        Role role = modelMapper.map(dto, Role.class);
        roleRepo.save(role);
    }
}
