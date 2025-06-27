package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.common.enums.SubjectStatus;
import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, String> {
    List<Subject> findByTeacher_TeacherId(String teacherId);
    List<Subject> findBySchoolAndSubjectStatus(School school, SubjectStatus status);
    boolean existsBySubjectIdAndSchool(String subjectId, School school);

    @Query("SELECT s FROM Subject s WHERE " +
           "(:subjectName IS NULL OR s.subjectName LIKE %:subjectName%) AND " +
           "(:subjectGrade IS NULL OR s.subjectGrade = :subjectGrade) AND " +
           "(:subjectSemester IS NULL OR s.subjectSemester = :subjectSemester) AND " +
           "(:subjectAffiliation IS NULL OR s.subjectAffiliation = :subjectAffiliation)")
    List<Subject> searchSubjects(@Param("subjectName") String subjectName,
                                @Param("subjectGrade") String subjectGrade,
                                @Param("subjectSemester") String subjectSemester,
                                @Param("subjectAffiliation") SubjectAffiliation subjectAffiliation);
}