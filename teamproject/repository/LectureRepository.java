package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.common.enums.LectureDayOfWeek;
import com.example.back404.teamproject.entity.Lecture;
import com.example.back404.teamproject.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    // 기존 메서드들
    @Query("SELECT l FROM Lecture l " +
            "JOIN FETCH l.subject s " +
            "JOIN FETCH l.teacher t " +
            "LEFT JOIN FETCH l.classroom c")
    List<Lecture> findAllWithSubjectAndTeacher();

    @Query("SELECT l FROM Lecture l " +
            "JOIN FETCH l.subject s " +
            "JOIN FETCH l.teacher t " +
            "LEFT JOIN FETCH l.classroom c " +
            "WHERE l.lectureId = :lectureId")
    Optional<Lecture> findByIdWithSubjectTeacherClassroom(@Param("lectureId") Long lectureId);

    List<Lecture> findBySubject_SubjectId(String subjectId);
    
    @Query("SELECT l FROM Lecture l WHERE l.teacher.teacherId = :teacherId")
    List<Lecture> findByTeacher_TeacherId(@Param("teacherId") String teacherId);

    // ========== 새로 추가된 메서드 - 시간표 중복 체크 ========== //
    boolean existsByTeacherAndLectureDayOfWeekAndLecturePeriod(
            Teacher teacher, LectureDayOfWeek dayOfWeek, Integer period);
    
    // 별칭 메서드
    default boolean existsByTeacherAndDayOfWeekAndPeriod(
            Teacher teacher, LectureDayOfWeek dayOfWeek, Integer period) {
        return existsByTeacherAndLectureDayOfWeekAndLecturePeriod(teacher, dayOfWeek, period);
    }
}