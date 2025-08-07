package com.example.high_school_course_registration.controller.subject;

import com.example.high_school_course_registration.common.ApiMappingPattern;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.subject.request.SubjectStatusUpdateRequestDto;
import com.example.high_school_course_registration.dto.subject.response.SubjectDetailDto;
import com.example.high_school_course_registration.dto.subject.response.SubjectSimpleDto;
import com.example.high_school_course_registration.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.API_MANAGE_SUBJECT)
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    // 과목 목록 검색 조회 (반환 LIST) - 교사 / 관리자
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<List<SubjectSimpleDto>>> searchSubjects(
            @AuthenticationPrincipal String email, // UserDetails 정보를 반환 (email, role 반환)
            @RequestParam(required = false) String subjectName, String grade, String semester, SubjectAffiliation subjectAffiliation
    ) {
        ResponseDto<List<SubjectSimpleDto>> results = subjectService.searchSubjects(email, subjectName, grade, semester, subjectAffiliation);
        return ResponseEntity.ok(results);
    }

    // 과목 상세 조회 (반환 단건) - 교사 / 관리자
    @GetMapping("/{subjectId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<SubjectDetailDto>> getSubjectById(
            @AuthenticationPrincipal String userId,
            @PathVariable String subjectId
    ) {
        ResponseDto<SubjectDetailDto> result = subjectService.getSubjectById(userId, subjectId);
        return ResponseEntity.ok(result);
    }

    // 과목 등록 상태 변경 (승인/거절/삭제)
    // PUT /api/v1/manage/subjects/{subjectId}/status
    @PutMapping("/{subjectId}/status")
    @PreAuthorize("hasRole('ADMIN')") // 관리자만 접근 가능
    public ResponseEntity<ResponseDto<SubjectDetailDto>> updateSubjectStatus(
            @AuthenticationPrincipal String username,
            @PathVariable String subjectId,
            @Valid @RequestBody SubjectStatusUpdateRequestDto requestDto // 새로운 DTO 사용
    ) {
        ResponseDto<SubjectDetailDto> response = subjectService.updateSubjectStatus(username, subjectId, requestDto.getStatus());
        return ResponseEntity.ok(response);
    }

    //# TeacherSubjectController에서 구현 (선우님 담당)
    // 과목 수정 - 교사 (프론트엔드 파트 담당)
}
