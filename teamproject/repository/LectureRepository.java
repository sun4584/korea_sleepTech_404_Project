package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    // 기존 메서드들
    @Query("SELECT l FROM Lecture l " +
            "JOIN FETCH l.subject s " +
            "JOIN FETCH l.teacher t " +
            "JOIN FETCH l.classroom c")
    List<Lecture> findAllWithSubjectAndTeacher();

    @Query("SELECT l FROM Lecture l " +
            "JOIN FETCH l.subject s " +
            "JOIN FETCH l.teacher t " +
            "JOIN FETCH l.classroom c " +
            "WHERE l.lectureId = :lectureId")
    Optional<Lecture> findByIdWithSubjectTeacherClassroom(Long lectureId);

    // 새로 추가된 메서드들
    List<Lecture> findBySubject_SubjectId(String subjectId);
    List<Lecture> findByTeacher_Id(Long teacherId);
}