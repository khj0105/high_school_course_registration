package com.high_school_course_registration.controller.notice;

import com.high_school_course_registration.common.ApiMappingPattern;
import com.high_school_course_registration.dto.common.ResponseDto;
import com.high_school_course_registration.dto.notice.request.NoticeCreateRequestDto;
import com.high_school_course_registration.dto.notice.request.NoticeUpdateRequestDto;
import com.high_school_course_registration.dto.notice.response.NoticeDetailDto;
import com.high_school_course_registration.service.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.SCHOOL_ADMIN_NOTICES)
@RequiredArgsConstructor
public class NoticeAdminController {

    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<ResponseDto<NoticeDetailDto>> createNotice(
            @Valid @RequestBody NoticeCreateRequestDto requestDto,
            @AuthenticationPrincipal String username) {
        NoticeDetailDto createdNotice = noticeService.createNotice(requestDto, username);
        return ResponseEntity.ok(ResponseDto.setSuccess("공지사항이 등록되었습니다.", createdNotice));
    }

    @PutMapping("/{noticeId}")
    public ResponseEntity<ResponseDto<NoticeDetailDto>> updateNotice(
            @PathVariable Long noticeId,
            @Valid @RequestBody NoticeUpdateRequestDto requestDto,
            @AuthenticationPrincipal String username) {
        NoticeDetailDto updatedNotice = noticeService.updateNotice(noticeId, requestDto, username);
        return ResponseEntity.ok(ResponseDto.setSuccess("공지사항이 수정되었습니다.", updatedNotice));
    }

    @DeleteMapping("/{noticeId}")
    public ResponseEntity<ResponseDto<Void>> deleteNotice(
            @PathVariable Long noticeId,
            @AuthenticationPrincipal String username) {
        noticeService.deleteNotice(noticeId, username);
        return ResponseEntity.ok(ResponseDto.setSuccess("공지사항이 삭제되었습니다.", null));
    }
}