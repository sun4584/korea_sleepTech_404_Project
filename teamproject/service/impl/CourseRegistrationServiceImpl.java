package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.ResponseMessage;
import com.example.back404.teamproject.common.enums.CourseRegistrationApprovalStatus;
import com.example.back404.teamproject.common.enums.ErrorCode;
import com.example.back404.teamproject.dto.registration.request.CourseRegistrationRequestDto;
import com.example.back404.teamproject.dto.registration.response.CourseRegistrationResponseDto;
import com.example.back404.teamproject.dto.registration.response.CourseRegistrationStudentListDto;
import com.example.back404.teamproject.dto.registration.response.EnrolledStudentListDto;
import com.example.back404.teamproject.dto.registration.response.LectureRegistrationSummaryDto;
import com.example.back404.teamproject.entity.CourseRegistration;
import com.example.back404.teamproject.entity.Lecture;
import com.example.back404.teamproject.entity.Student;
import com.example.back404.teamproject.exception.CustomException;
import com.example.back404.teamproject.repository.CourseRegistrationRepository;
import com.example.back404.teamproject.repository.LectureRepository;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.repository.StudentRepository;
import com.example.back404.teamproject.repository.TeacherRepository;
import com.example.back404.teamproject.service.CourseRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

    private final CourseRegistrationRepository courseRegistrationRepository;
    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;
    private final SchoolRepository schoolRepository;
    private final TeacherRepository teacherRepository;

    @Override
    @Transactional
    public void registerByEmail(String email, CourseRegistrationRequestDto requestDto) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        Lecture lecture = lectureRepository.findById(requestDto.getLectureId())
                .orElseThrow(() -> new CustomException(ErrorCode.LECTURE_NOT_FOUND));

        boolean alreadyRegistered = courseRegistrationRepository
                .existsByStudent_StudentIdAndLecture_LectureId(student.getStudentId(), requestDto.getLectureId());

        if (alreadyRegistered) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED);
        }

        if (lecture.getCourseRegistrations().size() >= lecture.getLectureMaxEnrollment()) {
            throw new CustomException(ErrorCode.LECTURE_FULL);
        }

        CourseRegistration registration = CourseRegistration.builder()
                .student(student)
                .lecture(lecture)
                .status(CourseRegistrationApprovalStatus.PENDING)
                .build();

        courseRegistrationRepository.save(registration);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseRegistrationResponseDto> getMyRegistrations(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        List<CourseRegistration> registrations = courseRegistrationRepository.findByStudent_StudentId(student.getStudentId());

        return registrations.stream()
                .map((CourseRegistration reg) -> CourseRegistrationResponseDto.builder()
                        .registrationId(reg.getRegistrationId())
                        .lectureId(reg.getLecture().getLectureId())
                        .lectureName(reg.getLecture().getSubject().getSubjectName())
                        .subjectName(reg.getLecture().getSubject().getSubjectName())
                        .teacherName(reg.getLecture().getTeacher().getTeacherName())
                        .dayOfWeek(reg.getLecture().getLectureDayOfWeek().name())
                        .period(reg.getLecture().getLecturePeriod())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CourseRegistrationResponseDto getRegistrationDetail(String email, Long registrationId) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        CourseRegistration reg = courseRegistrationRepository.findByRegistrationIdAndStudent_StudentId(registrationId, student.getStudentId())
                .orElseThrow(() -> new CustomException(ErrorCode.REGISTRATION_NOT_FOUND));

        return CourseRegistrationResponseDto.builder()
                .registrationId(reg.getRegistrationId())
                .lectureId(reg.getLecture().getLectureId())
                .lectureName(reg.getLecture().getSubject().getSubjectName())
                .subjectName(reg.getLecture().getSubject().getSubjectName())
                .teacherName(reg.getLecture().getTeacher().getTeacherName())
                .dayOfWeek(reg.getLecture().getLectureDayOfWeek().name())
                .period(reg.getLecture().getLecturePeriod())
                .build();
    }

    @Override
    @Transactional
    public void cancelRegistration(String email, Long registrationId) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        CourseRegistration reg = courseRegistrationRepository.findByRegistrationIdAndStudent_StudentId(registrationId, student.getStudentId())
                .orElseThrow(() -> new CustomException(ErrorCode.REGISTRATION_NOT_FOUND));

        courseRegistrationRepository.delete(reg);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<LectureRegistrationSummaryDto> getRegisteredStudentsByLectureId(String username, Long lectureId) {
        boolean isAuthorized = schoolRepository.existsBySchoolAdminUsername(username)
                || teacherRepository.existsByUsername(username);

        if (!isAuthorized) {
            throw new RuntimeException(ResponseMessage.NO_AUTHORITY + ": " + username);
        }

        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new RuntimeException(ResponseMessage.NOT_EXISTS_LECTURE + ": " + lectureId));

        int totalRegisteredCount = courseRegistrationRepository.countByLecture_LectureId(lectureId);
        List<CourseRegistration> registrations = courseRegistrationRepository.findByLecture_LectureIdWithStudent(lectureId);

        List<CourseRegistrationStudentListDto> registeredStudentsDto = registrations.stream()
                .map((CourseRegistration registration) -> {
                    Student student = registration.getStudent();
                    return CourseRegistrationStudentListDto.builder()
                            .studentId(student.getStudentId())
                            .studentNumber(student.getStudentNumber())
                            .studentName(student.getStudentName())
                            .studentGrade(student.getStudentGrade())
                            .studentEmail(student.getStudentEmail())
                            .courseRegistrationApprovalStatus(registration.getStatus())
                            .build();
                })
                .collect(Collectors.toList());

        LectureRegistrationSummaryDto summaryDto = LectureRegistrationSummaryDto.builder()
                .lectureId(lecture.getLectureId())
                .subjectName(lecture.getSubject().getSubjectName())
                .teacherName(lecture.getTeacher().getTeacherName())
                .lectureMaxEnrollment(lecture.getLectureMaxEnrollment())
                .currentRegisteredCount(totalRegisteredCount)
                .registeredStudents(registeredStudentsDto)
                .build();

        return ResponseDto.setSuccess(ResponseMessage.GET_REGISTERED_STUDENTS_SUCCESS, summaryDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<EnrolledStudentListDto>> getEnrolledStudentsByLectureId(String username, Long lectureId) {
        boolean isAuthorized = schoolRepository.existsBySchoolAdminUsername(username)
                || teacherRepository.existsByUsername(username);

        if (!isAuthorized) {
            throw new RuntimeException(ResponseMessage.NO_AUTHORITY + ": " + username);
        }

        boolean lectureExists = lectureRepository.existsById(lectureId);
        if (!lectureExists) {
            throw new RuntimeException(ResponseMessage.NOT_EXISTS_LECTURE + ": " + lectureId);
        }

        List<CourseRegistration> confirmedRegistrations = courseRegistrationRepository
                .findByLecture_LectureIdAndStatusApprovedWithStudent(lectureId);

        List<EnrolledStudentListDto> responseData = confirmedRegistrations.stream()
                .map((CourseRegistration registration) -> {
                    Student student = registration.getStudent();
                    return EnrolledStudentListDto.builder()
                            .studentId(student.getStudentId())
                            .studentNumber(student.getStudentNumber())
                            .studentName(student.getStudentName())
                            .studentGrade(student.getStudentGrade())
                            .studentBirthDate(student.getStudentBirthDate())
                            .build();
                })
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.GET_ENROLLED_STUDENTS_SUCCESS, responseData);
    }
}