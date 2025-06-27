package com.example.back404.teamproject.controller.teacher;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.lecture.request.LectureCreateRequestDto;
import com.example.back404.teamproject.dto.lecture.request.LectureUpdateRequestDto;
import com.example.back404.teamproject.dto.lecture.response.LectureListDto;
import com.example.back404.teamproject.dto.lecture.response.LectureSimpleResponseDto;
import com.example.back404.teamproject.provider.JwtProvider;
import com.example.back404.teamproject.service.LectureService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher/lectures")
@RequiredArgsConstructor
@PreAuthorize("hasRole('TEACHER')")
public class LectureManageController {

    private final LectureService lectureService;
    private final JwtProvider jwtProvider;

    @GetMapping
    public ResponseEntity<ResponseDto<List<LectureSimpleResponseDto>>> getMyLectures(
            HttpServletRequest request) {
        try {
            String teacherEmail = jwtProvider.getEmailFromRequest(request);
            List<LectureSimpleResponseDto> lectures = lectureService.getLecturesByTeacher(teacherEmail);
            return ResponseEntity.ok(ResponseDto.setSuccess("내 강의 목록 조회 성공", lectures));
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseDto.setFailed(e.getMessage()));
        }
    }

    // ========== 새로 추가된 강의 등록 API ========== //
    @PostMapping
    public ResponseEntity<ResponseDto<?>> createLecture(
            @Valid @RequestBody LectureCreateRequestDto requestDto,
            HttpServletRequest request) {
        try {
            String teacherEmail = jwtProvider.getEmailFromRequest(request);
            ResponseDto<?> response = lectureService.createLecture(requestDto, teacherEmail);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseDto.setFailed(e.getMessage()));
        }
    }

    @PutMapping("/{lectureId}")
    public ResponseEntity<ResponseDto<LectureListDto>> updateLecture(
            @PathVariable Long lectureId,
            @Valid @RequestBody LectureUpdateRequestDto requestDto) {
        try {
            ResponseDto<LectureListDto> response = lectureService.updateLecture(lectureId, requestDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseDto.setFailed(e.getMessage()));
        }
    }

    @DeleteMapping("/{lectureId}")
    public ResponseEntity<ResponseDto<?>> deleteLecture(@PathVariable Long lectureId) {
        try {
            ResponseDto<?> response = lectureService.deleteLecture(lectureId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseDto.setFailed(e.getMessage()));
        }
    }
}