package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    
    Optional<Teacher> findByTeacherEmail(String email);
    Optional<Teacher> findByTeacherUsername(String username);
    boolean existsByTeacherUsername(String username);
    boolean existsByTeacherEmail(String email);
    
    // 호환성을 위한 메서드들
    Optional<Teacher> findByEmail(String email);
    Optional<Teacher> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    
    Optional<Teacher> findByTeacherNameAndTeacherEmailAndTeacherPhoneNumber(String name, String email, String phoneNumber);
    Optional<Teacher> findByTeacherUsernameAndTeacherEmailAndTeacherName(String username, String email, String name);
    
    // 호환성을 위한 메서드들
    Optional<Teacher> findByNameAndEmailAndPhoneNumber(String name, String email, String phoneNumber);
    Optional<Teacher> findByUsernameAndEmailAndName(String username, String email, String name);
    
    List<Teacher> findBySchool(School school);
}