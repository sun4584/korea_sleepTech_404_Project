package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.enums.NoticeTargetAudience;
import com.example.back404.teamproject.dto.notice.request.NoticeCreateRequestDto;
import com.example.back404.teamproject.dto.notice.request.NoticeUpdateRequestDto;
import com.example.back404.teamproject.dto.notice.response.NoticeDetailResponseDto;
import com.example.back404.teamproject.dto.notice.response.NoticeListResponseDto;
import com.example.back404.teamproject.entity.Notice;
import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.repository.NoticeRepository;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final SchoolRepository schoolRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<NoticeListResponseDto>> getAllNotices(String userEmail) {
        try {
            School school = getSchoolByUserEmail(userEmail);
            
            List<Notice> notices = noticeRepository.findBySchoolOrderByCreatedAtDesc(school);
            
            List<NoticeListResponseDto> responseList = notices.stream()
                    .map(notice -> NoticeListResponseDto.builder()
                            .noticeId(notice.getNoticeId())
                            .title(notice.getTitle())
                            .targetAudience(notice.getTargetAudience().name())
                            .startDate(notice.getStartDate())
                            .endDate(notice.getEndDate())
                            .createdAt(notice.getCreatedAt())
                            .build())
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess("공지사항 목록 조회 성공", responseList);
        } catch (Exception e) {
            return ResponseDto.setFailed("공지사항 목록 조회 중 오류가 발생했습니다.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<NoticeListResponseDto>> getActiveNotices(String userEmail, String role) {
        try {
            School school = getSchoolByUserEmail(userEmail);
            
            NoticeTargetAudience targetAudience = null;
            if ("STUDENT".equals(role)) {
                targetAudience = NoticeTargetAudience.STUDENT;
            } else if ("TEACHER".equals(role)) {
                targetAudience = NoticeTargetAudience.TEACHER;
            }
            
            List<Notice> notices = noticeRepository.findActiveNotices(school, targetAudience, LocalDate.now());
            
            List<NoticeListResponseDto> responseList = notices.stream()
                    .map(notice -> NoticeListResponseDto.builder()
                            .noticeId(notice.getNoticeId())
                            .title(notice.getTitle())
                            .targetAudience(notice.getTargetAudience().name())
                            .startDate(notice.getStartDate())
                            .endDate(notice.getEndDate())
                            .createdAt(notice.getCreatedAt())
                            .build())
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess("활성 공지사항 조회 성공", responseList);
        } catch (Exception e) {
            return ResponseDto.setFailed("활성 공지사항 조회 중 오류가 발생했습니다.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<NoticeDetailResponseDto> getNoticeDetail(Long noticeId, String userEmail) {
        try {
            Notice notice = noticeRepository.findById(noticeId)
                    .orElseThrow(() -> new IllegalArgumentException("공지사항을 찾을 수 없습니다."));

            NoticeDetailResponseDto response = NoticeDetailResponseDto.builder()
                    .noticeId(notice.getNoticeId())
                    .title(notice.getTitle())
                    .content(notice.getContent())
                    .targetAudience(notice.getTargetAudience().name())
                    .startDate(notice.getStartDate())
                    .endDate(notice.getEndDate())
                    .createdAt(notice.getCreatedAt())
                    .updatedAt(notice.getUpdatedAt())
                    .build();

            return ResponseDto.setSuccess("공지사항 상세 조회 성공", response);
        } catch (Exception e) {
            return ResponseDto.setFailed("공지사항 상세 조회 중 오류가 발생했습니다.");
        }
    }

    @Override
    public ResponseDto<?> createNotice(NoticeCreateRequestDto requestDto, String userEmail) {
        try {
            School school = getSchoolByUserEmail(userEmail);

            Notice notice = Notice.builder()
                    .school(school)
                    .title(requestDto.getTitle())
                    .content(requestDto.getContent())
                    .targetAudience(NoticeTargetAudience.valueOf(requestDto.getTargetAudience()))
                    .startDate(LocalDate.parse(requestDto.getStartDate()))
                    .endDate(LocalDate.parse(requestDto.getEndDate()))
                    .build();

            noticeRepository.save(notice);
            return ResponseDto.setSuccess("공지사항 등록 성공", null);
        } catch (Exception e) {
            return ResponseDto.setFailed("공지사항 등록 중 오류가 발생했습니다.");
        }
    }

    @Override
    public ResponseDto<?> updateNotice(Long noticeId, NoticeUpdateRequestDto requestDto, String userEmail) {
        try {
            Notice notice = noticeRepository.findById(noticeId)
                    .orElseThrow(() -> new IllegalArgumentException("공지사항을 찾을 수 없습니다."));

            notice.update(
                    requestDto.getTitle(),
                    requestDto.getContent(),
                    requestDto.getTargetAudience() != null ? 
                        NoticeTargetAudience.valueOf(requestDto.getTargetAudience()) : notice.getTargetAudience(),
                    requestDto.getStartDate() != null ? 
                        LocalDate.parse(requestDto.getStartDate()) : notice.getStartDate(),
                    requestDto.getEndDate() != null ? 
                        LocalDate.parse(requestDto.getEndDate()) : notice.getEndDate()
            );

            noticeRepository.save(notice);
            return ResponseDto.setSuccess("공지사항 수정 성공", null);
        } catch (Exception e) {
            return ResponseDto.setFailed("공지사항 수정 중 오류가 발생했습니다.");
        }
    }

    @Override
    public ResponseDto<?> deleteNotice(Long noticeId, String userEmail) {
        try {
            Notice notice = noticeRepository.findById(noticeId)
                    .orElseThrow(() -> new IllegalArgumentException("공지사항을 찾을 수 없습니다."));

            noticeRepository.delete(notice);
            return ResponseDto.setSuccess("공지사항 삭제 성공", null);
        } catch (Exception e) {
            return ResponseDto.setFailed("공지사항 삭제 중 오류가 발생했습니다.");
        }
    }

    private School getSchoolByUserEmail(String userEmail) {
        // 실제 구현에서는 사용자 이메일로 학교 정보를 찾아야함 
        // 현재는 간단히 첫 번째 학교를 반환
        return schoolRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("학교 정보를 찾을 수 없습니다."));
    }
}