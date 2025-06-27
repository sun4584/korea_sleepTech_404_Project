package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.lecture.request.LectureUpdateRequestDto;
import com.example.back404.teamproject.dto.lecture.response.LectureDetailResponseDto;
import com.example.back404.teamproject.dto.lecture.response.LectureListDto;
import com.example.back404.teamproject.dto.lecture.response.LectureSimpleResponseDto;
import com.example.back404.teamproject.entity.Lecture;
import com.example.back404.teamproject.entity.Teacher;
import com.example.back404.teamproject.repository.LectureRepository;
import com.example.back404.teamproject.repository.TeacherRepository;
import com.example.back404.teamproject.service.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public List<LectureSimpleResponseDto> getAllLectures() {
        List<Lecture> lectures = lectureRepository.findAllWithSubjectAndTeacher();
        return lectures.stream()
                .map(lecture -> LectureSimpleResponseDto.builder()
                        .id(lecture.getLectureId())
                        .subjectName(lecture.getSubject().getSubjectName())
                        .teacherName(lecture.getTeacher().getTeacherName())
                        .dayOfWeek(lecture.getLectureDayOfWeek().toString())
                        .period(lecture.getLecturePeriod())
                        .maxStudents(lecture.getLectureMaxEnrollment())
                        .currentStudents(lecture.getCourseRegistrations().size())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public LectureDetailResponseDto getLectureDetail(Long lectureId) {
        Lecture lecture = lectureRepository.findByIdWithSubjectTeacherClassroom(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("강의를 찾을 수 없습니다."));

        return LectureDetailResponseDto.builder()
                .id(lecture.getLectureId())
                .subjectName(lecture.getSubject().getSubjectName())
                .subjectDescription(lecture.getSubject().getSubjectDescription())
                .teacherName(lecture.getTeacher().getTeacherName())
                .dayOfWeek(lecture.getLectureDayOfWeek().toString())
                .period(lecture.getLecturePeriod())
                .maxStudents(lecture.getLectureMaxEnrollment())
                .currentStudents(lecture.getCourseRegistrations().size())
                .classroom(lecture.getClassroom() != null ? lecture.getClassroom().getName() : "미배정")
                .build();
    }

    @Override
    @Transactional
    public ResponseDto<LectureListDto> updateLecture(Long lectureId, @Valid LectureUpdateRequestDto requestDto) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의를 찾을 수 없습니다."));

        Teacher teacher = teacherRepository.findById(Long.parseLong(requestDto.getTeacherId()))
                .orElseThrow(() -> new IllegalArgumentException("교사를 찾을 수 없습니다."));

        lecture.updateInfo(teacher, requestDto.getDayOfWeek(), requestDto.getPeriod(), requestDto.getMaxEnrollment());
        lectureRepository.save(lecture);

        LectureListDto dto = LectureListDto.builder()
                .lectureId(lecture.getLectureId())
                .subjectName(lecture.getSubject().getSubjectName())
                .teacherName(lecture.getTeacher().getTeacherName())
                .dayOfWeek(lecture.getLectureDayOfWeek())
                .period(lecture.getLecturePeriod())
                .allowedGrade(Integer.parseInt(lecture.getLectureAllowedGrade()))
                .build();

        return ResponseDto.setSuccess("강의 수정 성공", dto);
    }

    @Override
    @Transactional
    public ResponseDto<?> deleteLecture(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의를 찾을 수 없습니다."));

        lectureRepository.delete(lecture);
        return ResponseDto.setSuccess("강의 삭제 완료", null);
    }
}