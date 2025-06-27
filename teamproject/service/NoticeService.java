package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.notice.request.NoticeCreateRequestDto;
import com.example.back404.teamproject.dto.notice.request.NoticeUpdateRequestDto;
import com.example.back404.teamproject.dto.notice.response.NoticeDetailResponseDto;
import com.example.back404.teamproject.dto.notice.response.NoticeListResponseDto;

import java.util.List;

public interface NoticeService {
    ResponseDto<List<NoticeListResponseDto>> getAllNotices(String userEmail);
    ResponseDto<List<NoticeListResponseDto>> getActiveNotices(String userEmail, String role);
    ResponseDto<NoticeDetailResponseDto> getNoticeDetail(Long noticeId, String userEmail);
    ResponseDto<?> createNotice(NoticeCreateRequestDto requestDto, String userEmail);
    ResponseDto<?> updateNotice(Long noticeId, NoticeUpdateRequestDto requestDto, String userEmail);
    ResponseDto<?> deleteNotice(Long noticeId, String userEmail);
}