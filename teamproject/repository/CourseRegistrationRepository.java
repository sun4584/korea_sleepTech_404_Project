package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.common.enums.CourseRegistrationApprovalStatus;
import com.example.back404.teamproject.entity.CourseRegistration;
import com.example.back404.teamproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {

    boolean existsByStudent_StudentIdAndLecture_LectureId(String studentId, Long lectureId);

    List<CourseRegistration> findByStudent(Student student);

    List<CourseRegistration> findByStudent_StudentId(String studentId);

    Optional<CourseRegistration> findByRegistrationIdAndStudent_StudentId(Long registrationId, String studentId);

    // CourseRegistrationStatus 관련 메서드들을 CourseRegistrationApprovalStatus로 변경
    List<CourseRegistration> findByStudentAndStatus(Student student, CourseRegistrationApprovalStatus status);

    int countByLecture_LectureId(Long lectureId);

    @Query("SELECT cr FROM CourseRegistration cr JOIN FETCH cr.student WHERE cr.lecture.lectureId = :lectureId")
    List<CourseRegistration> findByLecture_LectureIdWithStudent(@Param("lectureId") Long lectureId);

    @Query("SELECT cr FROM CourseRegistration cr JOIN FETCH cr.student WHERE cr.lecture.lectureId = :lectureId AND cr.status = 'APPROVED'")
    List<CourseRegistration> findByLecture_LectureIdAndStatusApprovedWithStudent(@Param("lectureId") Long lectureId);
}