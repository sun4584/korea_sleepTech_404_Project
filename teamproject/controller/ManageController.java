package com.example.back404.teamproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manage")
@RequiredArgsConstructor
public class ManageController {

    // GET /api/v1/manage/teachers
    @GetMapping("/teachers")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<?> getAllTeachers() {
        return ResponseEntity.ok("교사 목록 조회");
    }

    // GET /api/v1/manage/students
    @GetMapping("/students")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok("학생 목록 조회");
    }

    // GET /api/v1/manage/students/{studentId}
    @GetMapping("/students/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<?> getStudentDetail(@PathVariable String studentId) {
        return ResponseEntity.ok("학생 정보 조회");
    }

    // GET /api/v1/manage/subjects
    @GetMapping("/subjects")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<?> getAllSubjects() {
        return ResponseEntity.ok("과목 목록 감시 조회");
    }

    // GET /api/v1/manage/subjects/{subjectId}
    @GetMapping("/subjects/{subjectId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<?> getSubjectDetail(@PathVariable String subjectId) {
        return ResponseEntity.ok("과목 상세 정보 조회");
    }

    // GET /api/v1/manage/lectures
    @GetMapping("/lectures")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<?> getAllLectures() {
        return ResponseEntity.ok("강의 목록 조회");
    }

    // GET /api/v1/manage/lectures/{lectureId}
    @GetMapping("/lectures/{lectureId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<?> getLectureDetail(@PathVariable Long lectureId) {
        return ResponseEntity.ok("강의 정보 조회");
    }

    // GET /api/v1/manage/lectures/{lectureId}/registrations-students
    @GetMapping("/lectures/{lectureId}/registrations-students")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<?> getRegisteredStudents(@PathVariable Long lectureId) {
        return ResponseEntity.ok("수강신청 학생 명단 조회");
    }

    // GET /api/v1/manage/lectures/{lectureId}/enrolled-students
    @GetMapping("/lectures/{lectureId}/enrolled-students")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<?> getEnrolledStudents(@PathVariable Long lectureId) {
        return ResponseEntity.ok("강의별 수강 학생 명단 조회");
    }

    // 기존 기능들 유지
    @PutMapping("/teacher/status")
    public ResponseEntity<?> updateTeacherStatus(@RequestBody Object dto) {
        return ResponseEntity.ok("교사 상태 변경");
    }

    @PutMapping("/student/status")
    public ResponseEntity<?> updateStudentStatus(@RequestBody Object dto) {
        return ResponseEntity.ok("학생 상태 변경");
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        return ResponseEntity.ok("교사 삭제");
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok("학생 삭제");
    }
}
