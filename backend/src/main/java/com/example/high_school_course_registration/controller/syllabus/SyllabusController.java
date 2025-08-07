package com.example.high_school_course_registration.controller.syllabus;

import com.example.high_school_course_registration.common.ApiMappingPattern;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.syllabus.request.SyllabusUpdateRequestDto;
import com.example.high_school_course_registration.dto.syllabus.response.SyllabusResponseDto;
import com.example.high_school_course_registration.service.SyllabusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.TEACHER_MY_COURSES + "/{courseId}/syllabus") // /api/v2/teacher/my-courses/{courseId}/syllabus
@RequiredArgsConstructor
public class SyllabusController {

    private final SyllabusService syllabusService;

    @GetMapping
    public ResponseEntity<ResponseDto<SyllabusResponseDto>> getSyllabus(
            @PathVariable Long courseId,
            @AuthenticationPrincipal String username) {
        SyllabusResponseDto responseDto = syllabusService.getSyllabus(courseId, username);
        return ResponseEntity.ok(ResponseDto.setSuccess("강의 계획서 조회 성공", responseDto));
    }

    @PutMapping
    public ResponseEntity<ResponseDto<SyllabusResponseDto>> updateSyllabus(
            @PathVariable Long courseId,
            @Valid @RequestBody SyllabusUpdateRequestDto requestDto,
            @AuthenticationPrincipal String username) {
        SyllabusResponseDto responseDto = syllabusService.updateSyllabus(courseId, requestDto, username);
        return ResponseEntity.ok(ResponseDto.setSuccess("강의 계획서가 저장되었습니다.", responseDto));
    }
}