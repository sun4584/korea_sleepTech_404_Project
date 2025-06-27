package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.lecture.request.LectureCreateRequestDto;
import com.example.back404.teamproject.dto.lecture.request.LectureUpdateRequestDto;
import com.example.back404.teamproject.dto.lecture.response.LectureDetailResponseDto;
import com.example.back404.teamproject.dto.lecture.response.LectureListDto;
import com.example.back404.teamproject.dto.lecture.response.LectureSimpleResponseDto;
import com.example.back404.teamproject.entity.Classroom;
import com.example.back404.teamproject.entity.Lecture;
import com.example.back404.teamproject.entity.Subject;
import com.example.back404.teamproject.entity.Teacher;
import com.example.back404.teamproject.repository.ClassroomRepository;
import com.example.back404.teamproject.repository.LectureRepository;
import com.example.back404.teamproject.repository.SubjectRepository;
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
    private final SubjectRepository subjectRepository;
    private final ClassroomRepository classroomRepository;

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
                        .currentStudents(lecture.getCourseRegistrations() != null ? lecture.getCourseRegistrations().size() : 0)
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
                .currentStudents(lecture.getCourseRegistrations() != null ? lecture.getCourseRegistrations().size() : 0)
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

    // ========== 새로 추가된 강의 등록 메서드 ========== //
    @Override
    @Transactional
    public ResponseDto<?> createLecture(LectureCreateRequestDto requestDto, String teacherEmail) {
        try {
            // 교사 정보 조회
            Teacher teacher = teacherRepository.findByTeacherEmail(teacherEmail)
                    .orElseThrow(() -> new IllegalArgumentException("교사 정보를 찾을 수 없습니다."));

            // 과목 정보 조회
            Subject subject = subjectRepository.findById(requestDto.getSubjectId())
                    .orElseThrow(() -> new IllegalArgumentException("과목 정보를 찾을 수 없습니다."));

            // 강의실 정보 조회 (선택사항)
            Classroom classroom = null;
            if (requestDto.getClassroomId() != null) {
                classroom = classroomRepository.findById(requestDto.getClassroomId())
                        .orElse(null);
            }

            // 시간표 중복 체크
            boolean timeConflict = lectureRepository.existsByTeacherAndDayOfWeekAndPeriod(
                    teacher, requestDto.getDayOfWeek(), requestDto.getPeriod());
            
            if (timeConflict) {
                return ResponseDto.setFailed("해당 시간에 이미 다른 강의가 있습니다.");
            }

            // 강의 생성
            Lecture lecture = Lecture.builder()
                    .school(teacher.getSchool())
                    .subject(subject)
                    .teacher(teacher)
                    .classroom(classroom)
                    .lectureDayOfWeek(requestDto.getDayOfWeek())
                    .lecturePeriod(requestDto.getPeriod())
                    .lectureAllowedGrade(requestDto.getAllowedGrade())
                    .lectureMaxEnrollment(requestDto.getMaxEnrollment())
                    .build();

            lectureRepository.save(lecture);
            return ResponseDto.setSuccess("강의가 성공적으로 등록되었습니다.", null);

        } catch (Exception e) {
            return ResponseDto.setFailed("강의 등록 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    @Override
    public List<LectureSimpleResponseDto> getLecturesByTeacher(String teacherEmail) {
        Teacher teacher = teacherRepository.findByTeacherEmail(teacherEmail)
                .orElseThrow(() -> new IllegalArgumentException("교사 정보를 찾을 수 없습니다."));

        List<Lecture> lectures = lectureRepository.findByTeacher_TeacherId(teacher.getTeacherId());
        
        return lectures.stream()
                .map(lecture -> LectureSimpleResponseDto.builder()
                        .id(lecture.getLectureId())
                        .subjectName(lecture.getSubject().getSubjectName())
                        .teacherName(lecture.getTeacher().getTeacherName())
                        .dayOfWeek(lecture.getLectureDayOfWeek().toString())
                        .period(lecture.getLecturePeriod())
                        .maxStudents(lecture.getLectureMaxEnrollment())
                        .currentStudents(lecture.getCourseRegistrations() != null ? lecture.getCourseRegistrations().size() : 0)
                        .build())
                .collect(Collectors.toList());
    }
}