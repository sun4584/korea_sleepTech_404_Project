package com.example.back404.teamproject.controller.registration;

import com.example.back404.teamproject.common.ApiMappingPattern;
import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.registration.response.EnrolledStudentListDto;
import com.example.back404.teamproject.dto.registration.response.LectureRegistrationSummaryDto;
import com.example.back404.teamproject.service.CourseRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.API_MANAGE_LECTURE) // 이제 정의됨
@RequiredArgsConstructor
public class CourseManageRegistrationController {

    private final CourseRegistrationService courseRegistrationService;

    @GetMapping("/{lectureId}/registrations-students")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<LectureRegistrationSummaryDto>> getRegisteredStudentsByLectureId(
            @AuthenticationPrincipal String username,
            @PathVariable Long lectureId
    ) {
        ResponseDto<LectureRegistrationSummaryDto> results = courseRegistrationService
                .getRegisteredStudentsByLectureId(username, lectureId);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{lectureId}/enrolled-students")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<List<EnrolledStudentListDto>>> getEnrolledStudentsByLectureId(
            @AuthenticationPrincipal String username,
            @PathVariable Long lectureId
    ) {
        ResponseDto<List<EnrolledStudentListDto>> results = courseRegistrationService
                .getEnrolledStudentsByLectureId(username, lectureId);
        return ResponseEntity.ok(results);
    }
}