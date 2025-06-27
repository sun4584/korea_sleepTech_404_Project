package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.enums.CourseRegistrationApprovalStatus;
import com.example.back404.teamproject.dto.schedule.response.ScheduleResponseDto;
import com.example.back404.teamproject.entity.CourseRegistration;
import com.example.back404.teamproject.entity.Lecture;
import com.example.back404.teamproject.entity.Student;
import com.example.back404.teamproject.repository.CourseRegistrationRepository;
import com.example.back404.teamproject.repository.StudentRepository;
import com.example.back404.teamproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final StudentRepository studentRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;

    @Override
    public List<ScheduleResponseDto> getSchedule(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 학생을 찾을 수 없습니다."));

        List<CourseRegistration> registrations = courseRegistrationRepository.findByStudentAndStatus(student, CourseRegistrationApprovalStatus.APPROVED);

        return registrations.stream()
                .map(registration -> {
                    Lecture lecture = registration.getLecture();
                    return ScheduleResponseDto.builder()
                            .dayOfWeek(lecture.getDayOfWeek().name())
                            .period(lecture.getPeriod())
                            .subjectName(lecture.getSubject().getSubjectName())
                            .lectureRoom(lecture.getClassroom() != null ? lecture.getClassroom().getName() : "미배정")
                            .build();
                })
                .collect(Collectors.toList());
    }
}