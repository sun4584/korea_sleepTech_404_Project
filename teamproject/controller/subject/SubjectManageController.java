package com.example.back404.teamproject.controller.subject;

import com.example.back404.teamproject.common.ApiMappingPattern;
import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.common.enums.SubjectStatus;
import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.subject.request.SubjectStatusUpdateRequestDto;
import com.example.back404.teamproject.dto.subject.response.SubjectGetResponseDto;
import com.example.back404.teamproject.dto.subject.response.SubjectListGetResponseDto;
import com.example.back404.teamproject.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.API_MANAGE_SUBJECT) // 이제 정의됨
@RequiredArgsConstructor
public class SubjectManageController {

    private final SubjectService subjectService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<List<SubjectListGetResponseDto>>> searchSubjects(
            @AuthenticationPrincipal String username,
            @RequestParam(required = false) String subjectName,
            @RequestParam(required = false) String subjectGrade,
            @RequestParam(required = false) String subjectSemester,
            @RequestParam(required = false) SubjectAffiliation subjectAffiliation
    ) {
        ResponseDto<List<SubjectListGetResponseDto>> results = subjectService.searchSubjects(
                username, subjectName, subjectGrade, subjectSemester, subjectAffiliation);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{subjectId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<SubjectGetResponseDto>> getSubjectById(
            @AuthenticationPrincipal String username,
            @PathVariable String subjectId
    ) {
        ResponseDto<SubjectGetResponseDto> result = subjectService.getSubjectById(username, subjectId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{subjectId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDto<SubjectGetResponseDto>> updateSubjectStatus(
            @AuthenticationPrincipal String username,
            @PathVariable String subjectId,
            @Valid @RequestBody SubjectStatusUpdateRequestDto requestDto
    ) {
        ResponseDto<SubjectGetResponseDto> response = subjectService.updateSubjectStatus(
                username, subjectId, requestDto.getStatus());
        return ResponseEntity.ok(response);
    }
}