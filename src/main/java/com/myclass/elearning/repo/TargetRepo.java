package com.myclass.elearning.repo;

import com.myclass.elearning.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetRepo extends JpaRepository<Target, Integer> {
}
