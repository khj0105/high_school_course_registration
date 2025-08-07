package com.example.high_school_course_registration.controller.notice;

import com.example.high_school_course_registration.common.ApiMappingPattern;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.notice.response.NoticeSimpleDto;
import com.example.high_school_course_registration.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.COMMON_NOTICES)
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<NoticeSimpleDto>>> getActiveNotices(
            @AuthenticationPrincipal String username) {
        List<NoticeSimpleDto> notices = noticeService.getActiveNotices(username);
        return ResponseEntity.ok(ResponseDto.setSuccess("공지사항 목록 조회 성공", notices));
    }
}