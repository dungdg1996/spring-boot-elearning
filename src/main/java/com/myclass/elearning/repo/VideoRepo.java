package com.myclass.elearning.repo;

import com.myclass.elearning.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepo extends JpaRepository<Video, Integer> {
}
