package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.ResponseMessage;
import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.dto.student.response.StudentGetResponseDto;
import com.example.back404.teamproject.dto.student.response.StudentListGetResponseDto;
import com.example.back404.teamproject.entity.Student;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.repository.StudentRepository;
import com.example.back404.teamproject.repository.TeacherRepository;
import com.example.back404.teamproject.service.StudentManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentManageServiceImpl implements StudentManageService {

    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public ResponseDto<StudentGetResponseDto> findByStudentId(String username, String studentId) {
        boolean isAuthorized = schoolRepository.existsBySchoolAdminUsername(username)
                || teacherRepository.existsByUsername(username);

        if (!isAuthorized) {
            throw new RuntimeException(ResponseMessage.NO_AUTHORITY + ": " + username);
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException(ResponseMessage.NOT_EXISTS_STUDENT + ": " + studentId));

        StudentGetResponseDto responseData = StudentGetResponseDto.builder()
                .studentId(student.getStudentId())
                .studentNumber(student.getStudentNumber())
                .studentName(student.getStudentName())
                .studentGrade(student.getStudentGrade())
                .studentEmail(student.getStudentEmail())
                .studentPhoneNumber(student.getStudentPhoneNumber())
                .studentBirthDate(student.getStudentBirthDate())
                .studentAffiliation(student.getStudentAffiliation())
                .studentAdmissionYear(student.getStudentAdmissionYear())
                .studentStatus(student.getStudentStatus())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.GET_STUDENT_DETAIL_SUCCESS, responseData);
    }

    @Override
    public ResponseDto<List<StudentListGetResponseDto>> searchStudents(
            String username, String studentNumber, String studentName, 
            String studentGrade, SubjectAffiliation studentAffiliation) {
        
        boolean isAuthorized = schoolRepository.existsBySchoolAdminUsername(username)
                || teacherRepository.existsByUsername(username);

        if (!isAuthorized) {
            throw new RuntimeException(ResponseMessage.NO_AUTHORITY + ": " + username);
        }

        List<Student> students = studentRepository.searchStudents(
                studentNumber, studentName, studentGrade, studentAffiliation);

        List<StudentListGetResponseDto> responseData = students.stream()
                .map(student -> StudentListGetResponseDto.builder()
                        .studentId(student.getStudentId())
                        .studentNumber(student.getStudentNumber())
                        .studentName(student.getStudentName())
                        .studentGrade(student.getStudentGrade())
                        .studentEmail(student.getStudentEmail())
                        .studentAffiliation(student.getStudentAffiliation())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.GET_STUDENT_LIST_SUCCESS, responseData);
    }
}