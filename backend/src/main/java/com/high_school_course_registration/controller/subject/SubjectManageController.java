package com.high_school_course_registration.controller.subject;

import com.high_school_course_registration.common.ApiMappingPattern;
import com.high_school_course_registration.dto.common.ResponseDto;
import com.high_school_course_registration.dto.subject.request.SubjectApprovalRequestDto;
import com.high_school_course_registration.dto.subject.response.SubjectDetailDto;
import com.high_school_course_registration.dto.subject.response.SubjectSimpleDto;
import com.high_school_course_registration.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.SCHOOL_ADMIN_SUBJECTS) // /api/v2/school-admin/subjects
@RequiredArgsConstructor
public class SubjectManageController {

    private final SubjectService subjectService;

    @PutMapping("/{subjectId}/status")
    public ResponseEntity<ResponseDto<SubjectDetailDto>> updateSubjectStatus(
            @PathVariable Long subjectId,
            @Valid @RequestBody SubjectApprovalRequestDto requestDto, // ✅ DTO 변경
            @AuthenticationPrincipal String username) {
        SubjectDetailDto responseDto = subjectService.updateSubjectStatus(subjectId, requestDto, username);
        return ResponseEntity.ok(ResponseDto.setSuccess("과목 상태가 변경되었습니다.", responseDto));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<SubjectSimpleDto>>> getAllSubjectsInSchool(
            @AuthenticationPrincipal String username) {
        List<SubjectSimpleDto> responseDto = subjectService.getAllSubjectsInSchool(username);
        return ResponseEntity.ok(ResponseDto.setSuccess("전체 과목 목록 조회 성공", responseDto));
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<ResponseDto<SubjectDetailDto>> getSubjectById(
            @PathVariable Long subjectId,
            @AuthenticationPrincipal String username) {
        SubjectDetailDto responseDto = subjectService.getSubjectById(subjectId, username);
        return ResponseEntity.ok(ResponseDto.setSuccess("과목 상세 정보 조회 성공", responseDto));
    }
}