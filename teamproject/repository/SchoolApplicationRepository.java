package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.entity.SchoolApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolApplicationRepository extends JpaRepository<SchoolApplication, Long> {
    boolean existsBySchoolCode(Integer schoolCode);
}