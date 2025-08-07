package com.example.high_school_course_registration.controller.subject;

import com.example.high_school_course_registration.common.ApiMappingPattern;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.subject.request.SubjectStatusUpdateRequestDto;
import com.example.high_school_course_registration.dto.subject.response.SubjectDetailDto;
import com.example.high_school_course_registration.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.SCHOOL_ADMIN_SUBJECTS) // /api/v2/school-admin/subjects
@RequiredArgsConstructor
public class SubjectManageController {

    private final SubjectService subjectService;

    // 과목 상태 변경 (승인/거절)
    @PatchMapping("/{subjectId}/status")
    public ResponseEntity<ResponseDto<SubjectDetailDto>> updateSubjectStatus(
            @PathVariable Long subjectId,
            @Valid @RequestBody SubjectStatusUpdateRequestDto requestDto,
            @AuthenticationPrincipal String username) {
        SubjectDetailDto responseDto = subjectService.updateSubjectStatus(subjectId, requestDto.getStatus(), username);
        return ResponseEntity.ok(ResponseDto.setSuccess("과목 상태가 변경되었습니다.", responseDto));
    }

    // To-Do: 전체 과목 조회, 상세 조회 API 추가
}