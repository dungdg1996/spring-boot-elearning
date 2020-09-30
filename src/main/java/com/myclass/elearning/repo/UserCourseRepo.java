package com.myclass.elearning.repo;

import com.myclass.elearning.entity.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCourseRepo extends JpaRepository<UserCourse, Integer> {
}
