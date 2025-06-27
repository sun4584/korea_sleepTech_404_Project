package com.example.back404.teamproject.controller.subject;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.subject.request.SubjectCreateRequestDto;
import com.example.back404.teamproject.dto.subject.request.SubjectUpdateRequestDto;
import com.example.back404.teamproject.provider.JwtProvider;
import com.example.back404.teamproject.service.SubjectManagementService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectManagementController {

    private final SubjectManagementService subjectManagementService;
    private final JwtProvider jwtProvider;

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