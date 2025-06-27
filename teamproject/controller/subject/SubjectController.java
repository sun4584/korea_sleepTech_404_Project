package com.example.back404.teamproject.controller.subject;

import com.example.back404.teamproject.common.ApiMappingPattern;
import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.dto.subject.request.SubjectCreateRequestDto;
import com.example.back404.teamproject.dto.subject.request.SubjectUpdateRequestDto;
import com.example.back404.teamproject.dto.subject.response.SubjectGetResponseDto;
import com.example.back404.teamproject.dto.subject.response.SubjectListGetResponseDto;
import com.example.back404.teamproject.provider.JwtProvider;
import com.example.back404.teamproject.service.SubjectService;
import com.example.back404.teamproject.service.SubjectManagementService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.SUBJECTS_BASE) // 이제 정의됨
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;
    private final SubjectManagementService subjectManagementService;
    private final JwtProvider jwtProvider;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<List<SubjectListGetResponseDto>>> searchSubjects(
            @AuthenticationPrincipal String email,
            @RequestParam(required = false) String subjectName,
            @RequestParam(required = false) String grade,
            @RequestParam(required = false) String semester,
            @RequestParam(required = false) SubjectAffiliation subjectAffiliation
    ) {
        ResponseDto<List<SubjectListGetResponseDto>> results = subjectService.searchSubjects(
                email, subjectName, grade, semester, subjectAffiliation);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{subjectId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<SubjectGetResponseDto>> getSubjectById(
            @AuthenticationPrincipal String userId,
            @PathVariable String subjectId
    ) {
        ResponseDto<SubjectGetResponseDto> result = subjectService.getSubjectById(userId, subjectId);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ResponseDto<?>> createSubject(
            @Valid @RequestBody SubjectCreateRequestDto requestDto,
            HttpServletRequest request) {
        
        String teacherEmail = jwtProvider.getEmailFromRequest(request);
        ResponseDto<?> response = subjectManagementService.createSubject(requestDto, teacherEmail);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{subjectId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ResponseDto<?>> updateSubject(
            @PathVariable String subjectId,
            @Valid @RequestBody SubjectUpdateRequestDto requestDto,
            HttpServletRequest request) {
        
        String teacherEmail = jwtProvider.getEmailFromRequest(request);
        ResponseDto<?> response = subjectManagementService.updateSubject(subjectId, requestDto, teacherEmail);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{subjectId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ResponseDto<?>> deleteSubject(
            @PathVariable String subjectId,
            HttpServletRequest request) {
        
        String teacherEmail = jwtProvider.getEmailFromRequest(request);
        ResponseDto<?> response = subjectManagementService.deleteSubject(subjectId, teacherEmail);
        return ResponseEntity.ok(response);
    }
}