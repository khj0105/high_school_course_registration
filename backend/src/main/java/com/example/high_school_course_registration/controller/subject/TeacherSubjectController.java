package com.example.high_school_course_registration.controller.subject;

import com.example.high_school_course_registration.common.ApiMappingPattern;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.subject.request.SubjectCreateRequestDto;
import com.example.high_school_course_registration.dto.subject.request.SubjectUpdateRequestDto;
import com.example.high_school_course_registration.dto.subject.response.SubjectDetailDto;
import com.example.high_school_course_registration.dto.subject.response.SubjectSimpleDto;
import com.example.high_school_course_registration.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.TEACHER_SUBJECTS) // /api/v2/teacher/subjects
@RequiredArgsConstructor
public class TeacherSubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<ResponseDto<SubjectDetailDto>> createSubject(
            @Valid @RequestBody SubjectCreateRequestDto requestDto,
            @AuthenticationPrincipal String username) {
        SubjectDetailDto responseDto = subjectService.createSubject(requestDto, username);
        return ResponseEntity.ok(ResponseDto.setSuccess("과목 신청이 완료되었습니다.", responseDto));
    }

    @PutMapping("/{subjectId}")
    public ResponseEntity<ResponseDto<SubjectDetailDto>> updateMySubject(
            @PathVariable Long subjectId,
            @Valid @RequestBody SubjectUpdateRequestDto requestDto,
            @AuthenticationPrincipal String username) {
        SubjectDetailDto responseDto = subjectService.updateMySubject(subjectId, requestDto, username);
        return ResponseEntity.ok(ResponseDto.setSuccess("과목 정보가 수정되었습니다.", responseDto));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<SubjectSimpleDto>>> getMySubjects(
            @AuthenticationPrincipal String username) {
        List<SubjectSimpleDto> responseDto = subjectService.getMySubjects(username);
        return ResponseEntity.ok(ResponseDto.setSuccess("나의 과목 신청 목록 조회 성공", responseDto));
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<ResponseDto<SubjectDetailDto>> getMySubjectById(
            @PathVariable Long subjectId,
            @AuthenticationPrincipal String username) {
        SubjectDetailDto responseDto = subjectService.getMySubjectById(subjectId, username);
        return ResponseEntity.ok(ResponseDto.setSuccess("과목 상세 정보 조회 성공", responseDto));
    }
}