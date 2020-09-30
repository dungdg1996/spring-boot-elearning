package com.myclass.elearning.repo;

import com.myclass.elearning.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
    @Query("select c from Course c where ((:id is null) or c.categoryId = :id )" +
            "and ((:key is null ) or c.title like :key)")
    List<Course> search(Integer id, String key);

    @Query("select u.courses from User u where u.email = :email")
    List<Course> findByUser(String email);
}
