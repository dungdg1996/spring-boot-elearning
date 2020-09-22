package com.myclass.elearning.repo;

import com.myclass.elearning.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
    List<Course> findAllByCategoryId(int id);
}
