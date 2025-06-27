package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.enums.StudentStatus;
import com.example.back404.teamproject.common.enums.TeacherStatus;
import com.example.back404.teamproject.dto.student.request.StudentStatusUpdateRequestDto;
import com.example.back404.teamproject.dto.teacher.request.TeacherStatusUpdateRequestDto;
import com.example.back404.teamproject.entity.Student;
import com.example.back404.teamproject.entity.Teacher;
import com.example.back404.teamproject.repository.StudentRepository;
import com.example.back404.teamproject.repository.TeacherRepository;
import com.example.back404.teamproject.service.ManageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManageServiceImpl implements ManageService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public ResponseDto<?> updateTeacherStatus(TeacherStatusUpdateRequestDto dto) {
        try {
            Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new IllegalArgumentException("교사를 찾을 수 없습니다."));

            TeacherStatus status = TeacherStatus.valueOf(dto.getStatus().toUpperCase());
            teacher.setStatus(status);
            teacherRepository.save(teacher);

            return ResponseDto.setSuccess("교사 상태 변경 완료", null);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("교사를 찾을 수 없습니다")) {
                return ResponseDto.setFailed(e.getMessage());
            }
            return ResponseDto.setFailed("유효하지 않은 교사 상태입니다.");
        } catch (Exception e) {
            return ResponseDto.setFailed("교사 상태 변경 중 오류가 발생했습니다.");
        }
    }

    @Override
    @Transactional
    public ResponseDto<?> updateStudentStatus(StudentStatusUpdateRequestDto dto) {
        try {
            // Long을 String으로 변환
            Student student = studentRepository.findById(String.valueOf(dto.getStudentId()))
                    .orElseThrow(() -> new IllegalArgumentException("학생을 찾을 수 없습니다."));

            StudentStatus status = StudentStatus.valueOf(dto.getStatus().toUpperCase());
            student.setStatus(status);
            studentRepository.save(student);

            return ResponseDto.setSuccess("학생 상태 변경 완료", null);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("학생을 찾을 수 없습니다")) {
                return ResponseDto.setFailed(e.getMessage());
            }
            return ResponseDto.setFailed("유효하지 않은 학생 상태입니다.");
        } catch (Exception e) {
            return ResponseDto.setFailed("학생 상태 변경 중 오류가 발생했습니다.");
        }
    }

    @Override
    @Transactional
    public ResponseDto<?> deleteTeacher(Long id) {
        try {
            if (!teacherRepository.existsById(id)) {
                return ResponseDto.setFailed("교사를 찾을 수 없습니다.");
            }
            teacherRepository.deleteById(id);
            return ResponseDto.setSuccess("교사 삭제 완료", null);
        } catch (Exception e) {
            return ResponseDto.setFailed("교사 삭제 중 오류가 발생했습니다.");
        }
    }

    @Override
    @Transactional
    public ResponseDto<?> deleteStudent(Long id) {
        try {
            // Long을 String으로 변환
            String studentId = String.valueOf(id);
            if (!studentRepository.existsById(studentId)) {
                return ResponseDto.setFailed("학생을 찾을 수 없습니다.");
            }
            studentRepository.deleteById(studentId);
            return ResponseDto.setSuccess("학생 삭제 완료", null);
        } catch (Exception e) {
            return ResponseDto.setFailed("학생 삭제 중 오류가 발생했습니다.");
        }
    }
}