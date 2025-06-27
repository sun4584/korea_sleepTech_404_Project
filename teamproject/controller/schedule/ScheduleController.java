package com.example.back404.teamproject.controller.schedule;

import com.example.back404.teamproject.common.ApiMappingPattern;
import com.example.back404.teamproject.dto.schedule.response.ScheduleResponseDto;
import com.example.back404.teamproject.provider.JwtProvider;
import com.example.back404.teamproject.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.STUDENT_SCHEDULES) // 이제 정의됨
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final JwtProvider jwtTokenProvider;

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getMySchedule(HttpServletRequest request) {
        String email = jwtTokenProvider.getEmailFromRequest(request);
        List<ScheduleResponseDto> schedule = scheduleService.getSchedule(email);
        return ResponseEntity.ok(schedule);
    }
}