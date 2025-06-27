package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {  // String으로 변경
    Optional<Student> findByStudentUsername(String username);
    Optional<Student> findByStudentEmail(String email);
    boolean existsByStudentUsername(String username);
    boolean existsByStudentEmail(String email);
    boolean existsByStudentNumber(String studentNumber);
    
    // 호환성을 위한 메서드들
    Optional<Student> findByUsername(String username);
    Optional<Student> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    
    // 새로 추가된 메서드들
    Optional<Student> findByStudentNameAndStudentEmailAndStudentNumber(String name, String email, String studentNumber);
    Optional<Student> findByStudentUsernameAndStudentEmailAndStudentNumber(String username, String email, String studentNumber);
    
    // 호환성을 위한 메서드들
    Optional<Student> findByNameAndEmailAndStudentNumber(String name, String email, String studentNumber);
    Optional<Student> findByUsernameAndEmailAndStudentNumber(String username, String email, String studentNumber);

    @Query("SELECT s FROM Student s WHERE " +
           "(:studentNumber IS NULL OR s.studentNumber LIKE %:studentNumber%) AND " +
           "(:studentName IS NULL OR s.studentName LIKE %:studentName%) AND " +
           "(:studentGrade IS NULL OR s.studentGrade = :studentGrade) AND " +
           "(:studentAffiliation IS NULL OR s.studentAffiliation = :studentAffiliation)")
    List<Student> searchStudents(@Param("studentNumber") String studentNumber,
                                @Param("studentName") String studentName,
                                @Param("studentGrade") String studentGrade,
                                @Param("studentAffiliation") SubjectAffiliation studentAffiliation);
}