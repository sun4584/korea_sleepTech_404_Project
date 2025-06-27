package com.example.back404.teamproject.service;

import com.example.back404.teamproject.dto.schedule.response.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    List<ScheduleResponseDto> getSchedule(String email);
}