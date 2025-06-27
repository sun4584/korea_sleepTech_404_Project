package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.enums.StudentStatus;
import com.example.back404.teamproject.dto.auth.request.LoginRequestDto;
import com.example.back404.teamproject.dto.student.request.StudentSignUpRequestDto;
import com.example.back404.teamproject.dto.student.request.StudentUpdateRequestDto;
import com.example.back404.teamproject.dto.student.response.StudentInfoResponseDto;
import com.example.back404.teamproject.entity.Student;
import com.example.back404.teamproject.repository.StudentRepository;
import com.example.back404.teamproject.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void signUp(StudentSignUpRequestDto request) {
        if (studentRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }

        if (studentRepository.existsByStudentNumber(request.getStudentNumber())) {
            throw new IllegalArgumentException("이미 등록된 학번입니다.");
        }

        LocalDate birth;
        try {
            birth = LocalDate.parse(request.getBirthDate());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("생년월일 형식이 잘못되었습니다. (예: 2006-09-01)");
        }

        Student student = Student.builder()
                .studentId("S" + System.currentTimeMillis())
                .studentUsername(request.getUsername())
                .studentPassword(passwordEncoder.encode(request.getPassword()))
                .studentNumber(request.getStudentNumber())
                .studentName(request.getName())
                .studentGrade(String.valueOf(request.getGrade()))
                .studentEmail(request.getEmail())
                .studentPhoneNumber(request.getPhoneNumber())
                .studentBirthDate(birth)
                .studentAffiliation(request.getAffiliation())
                .studentStatus(StudentStatus.PENDING)
                .studentAdmissionYear(Year.of(request.getAdmissionYear()))
                .build();

        studentRepository.save(student);
    }

    @Override
    public Student login(LoginRequestDto request) {
        Student student = studentRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return student;
    }

    @Override
    public StudentInfoResponseDto getStudentInfo(Long studentId) {
        Student student = studentRepository.findById(String.valueOf(studentId))
                .orElseThrow(() -> new IllegalArgumentException("학생을 찾을 수 없습니다."));
        return StudentInfoResponseDto.from(student);
    }

    @Override
    @Transactional
    public void updateStudentInfo(Long studentId, StudentUpdateRequestDto request) {
        Student student = studentRepository.findById(String.valueOf(studentId))
                .orElseThrow(() -> new IllegalArgumentException("학생을 찾을 수 없습니다."));

        student.update(
                request.getName(),
                request.getPhoneNumber(),
                request.getEmail()
        );

        studentRepository.save(student);
    }
}