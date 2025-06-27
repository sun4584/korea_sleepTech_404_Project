package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.ResponseMessage;
import com.example.back404.teamproject.dto.common.SchoolListDto;
import com.example.back404.teamproject.dto.school.request.SchoolUpdateRequestDto;
import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.service.SchoolService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    @Override
    public ResponseDto<?> getSchoolInfo(Long id) {
        try {
            School school = schoolRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("학교를 찾을 수 없습니다."));
            return ResponseDto.setSuccess("학교 정보 조회 완료", school);
        } catch (Exception e) {
            return ResponseDto.setFailed("학교 정보 조회 중 오류가 발생했습니다.");
        }
    }

    @Override
    @Transactional
    public ResponseDto<?> updateSchoolInfo(Long id, SchoolUpdateRequestDto dto) {
        try {
            School school = schoolRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("학교를 찾을 수 없습니다."));

            if (dto.getSchoolAddress() != null) {
                school.setSchoolAddress(dto.getSchoolAddress());
            }
            if (dto.getSchoolContactNumber() != null) {
                school.setSchoolContactNumber(dto.getSchoolContactNumber());
            }

            schoolRepository.save(school);
            return ResponseDto.setSuccess("학교 정보 수정 완료", null);
        } catch (Exception e) {
            return ResponseDto.setFailed("학교 정보 수정 중 오류가 발생했습니다.");
        }
    }

    @Override
    public ResponseDto<List<SchoolListDto>> getAllSchools() {
        try {
            List<School> schools = schoolRepository.findAll();
            List<SchoolListDto> schoolList = schools.stream()
                    .map(school -> SchoolListDto.builder()
                            .schoolId(school.getSchoolId())
                            .schoolCode(school.getSchoolCode().toString())
                            .schoolName(school.getSchoolName())
                            .build())
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess(ResponseMessage.GET_SCHOOL_LIST_SUCCESS, schoolList);
        } catch (Exception e) {
            return ResponseDto.setFailed("학교 목록 조회 중 오류가 발생했습니다.");
        }
    }
}