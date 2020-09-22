package com.myclass.elearning.service.imp;

import com.myclass.elearning.dto.UserChangePasswordDto;
import com.myclass.elearning.dto.UserPostDto;
import com.myclass.elearning.dto.UserPutDto;
import com.myclass.elearning.entity.User;
import com.myclass.elearning.exception.UserNotFoundException;
import com.myclass.elearning.repo.UserRepo;
import com.myclass.elearning.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public UserServiceImp(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Không tìm thấy user với id : " + id));
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public void save(UserPostDto dto) {
        User user = modelMapper.map(dto, User.class);
        userRepo.save(user);
    }

    @Override
    public void delete(Integer id) {
        User user = findById(id);
        userRepo.delete(user);
    }

    @Override
    public void changePassword(UserChangePasswordDto dto) {
        User user = findById(dto.getUserId());
        if (dto.getOdlPassword().equals(user.getPassword())){
            user.setPassword(dto.getNewPassword());
            userRepo.save(user);
        } else throw new RuntimeException("Mật khẩu không đúng");
    }

    @Override
    public void save(UserPutDto dto) {
        User user = userRepo.findById(dto.getId()).get();
        user.setEmail(dto.getEmail());
        user.setFullname(dto.getFullname());
        user.setAvatar(dto.getAvatar());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setRoleId(dto.getRoleId());
        userRepo.save(user);
    }
}
