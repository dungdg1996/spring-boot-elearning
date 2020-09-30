package com.myclass.elearning.service.imp;

import com.myclass.elearning.dto.*;
import com.myclass.elearning.entity.Course;
import com.myclass.elearning.entity.User;
import com.myclass.elearning.entity.UserCourse;
import com.myclass.elearning.repo.CourseRepo;
import com.myclass.elearning.repo.UserCourseRepo;
import com.myclass.elearning.repo.UserRepo;
import com.myclass.elearning.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    private final UserRepo userRepo;
    private final CourseRepo courseRepo;
    private final UserCourseRepo userCourseRepo;
    private final ModelMapper modelMapper;

    public UserServiceImp(UserRepo userRepo, CourseRepo courseRepo, UserCourseRepo userCourseRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
        this.userCourseRepo = userCourseRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user với id : " + id));
    }

    @Override
    public UserGetDto getDto(Integer id) {
        return modelMapper.map(findById(id), UserGetDto.class);
    }

    @Override
    public List<UserGetDto> getAllDto() {
        return userRepo.findAll().stream()
                .map(user -> modelMapper.map(user, UserGetDto.class))
                .collect(Collectors.toList());
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
    public void save(UserChangePasswordDto dto) {
        User user = findByEmail(dto.getEmail());
        if (dto.getOdlPassword().equals(user.getPassword())) {
            user.setPassword(dto.getNewPassword());
            userRepo.save(user);
        } else throw new RuntimeException("Mật khẩu không đúng");
    }

    @Override
    public void save(UserRegisterDto dto) {
        User user = modelMapper.map(dto, User.class);
        user.setRoleId(3);
        userRepo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    @Override
    public void addCourse(Integer userId, Integer courseId) {
        UserCourse userCourse = new UserCourse();
        userCourse.setCourseId(courseId);
        userCourse.setUserId(userId);
        userCourseRepo.save(userCourse);
    }

    @Override
    public void save(UserPutDto dto) {
        User user = userRepo.findById(dto.getId()).get();
        user.setEmail(dto.getEmail());
        user.setFullname(dto.getFullname());
        user.setAddress(dto.getAddress());
        user.setPhone(dto.getPhone());
        user.setFacebook(dto.getFacebook());
        user.setWebsite(dto.getWebsite());
        userRepo.save(user);
    }
}
