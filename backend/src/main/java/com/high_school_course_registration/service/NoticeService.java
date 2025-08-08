package com.high_school_course_registration.service;

import com.high_school_course_registration.dto.notice.request.NoticeCreateRequestDto;
import com.high_school_course_registration.dto.notice.request.NoticeUpdateRequestDto;
import com.high_school_course_registration.dto.notice.response.NoticeDetailDto;
import com.high_school_course_registration.dto.notice.response.NoticeSimpleDto;
import java.util.List;

public interface NoticeService {

    NoticeDetailDto createNotice(NoticeCreateRequestDto requestDto, String username);
    NoticeDetailDto updateNotice(Long noticeId, NoticeUpdateRequestDto requestDto, String username);
    void deleteNotice(Long noticeId, String username);


    List<NoticeSimpleDto> getActiveNotices(String username);
}