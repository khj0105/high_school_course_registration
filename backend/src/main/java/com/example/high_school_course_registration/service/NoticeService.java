package com.example.high_school_course_registration.service;

import com.example.high_school_course_registration.dto.notice.request.NoticeCreateRequestDto;
import com.example.high_school_course_registration.dto.notice.request.NoticeUpdateRequestDto;
import com.example.high_school_course_registration.dto.notice.response.NoticeDetailDto;
import com.example.high_school_course_registration.dto.notice.response.NoticeSimpleDto;
import java.util.List;

public interface NoticeService {

    NoticeDetailDto createNotice(NoticeCreateRequestDto requestDto, String username);
    NoticeDetailDto updateNotice(Long noticeId, NoticeUpdateRequestDto requestDto, String username);
    void deleteNotice(Long noticeId, String username);


    List<NoticeSimpleDto> getActiveNotices(String username);
}