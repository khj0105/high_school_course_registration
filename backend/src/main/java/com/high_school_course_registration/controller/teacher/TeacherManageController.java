package com.high_school_course_registration.controller.teacher;

import com.high_school_course_registration.common.ApiMappingPattern;
import com.high_school_course_registration.dto.common.ResponseDto;
import com.high_school_course_registration.dto.teacher.request.TeacherCreateRequestDto;
import com.high_school_course_registration.dto.teacher.request.TeacherStatusUpdateRequestDto;
import com.high_school_course_registration.dto.teacher.response.TeacherDetailDto;
import com.high_school_course_registration.dto.teacher.response.TeacherSimpleDto;
import com.high_school_course_registration.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.SCHOOL_ADMIN_TEACHERS) // /api/v2/school-admin/teachers
@RequiredArgsConstructor
public class TeacherManageController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<ResponseDto<TeacherDetailDto>> createTeacher(
            @Valid @RequestBody TeacherCreateRequestDto requestDto,
            @AuthenticationPrincipal String adminUsername) {
        TeacherDetailDto responseDto = teacherService.createTeacher(requestDto, adminUsername);
        return ResponseEntity.ok(ResponseDto.setSuccess("교사 계정이 생성되었습니다.", responseDto));
    }

    @GetMapping("/pending")
    public ResponseEntity<ResponseDto<List<TeacherSimpleDto>>> getPendingTeachers(
            @AuthenticationPrincipal String adminUsername) {
        List<TeacherSimpleDto> responseDto = teacherService.getPendingTeachers(adminUsername);
        return ResponseEntity.ok(ResponseDto.setSuccess("승인 대기 교사 목록 조회 성공", responseDto));
    }

    @PutMapping("/{teacherId}/status")
    public ResponseEntity<ResponseDto<TeacherDetailDto>> updateTeacherStatus(
            @PathVariable Long teacherId,
            @Valid @RequestBody TeacherStatusUpdateRequestDto requestDto,
            @AuthenticationPrincipal String adminUsername) {
        TeacherDetailDto responseDto = teacherService.updateTeacherStatus(teacherId, requestDto.getTeacherStatus(), adminUsername);
        return ResponseEntity.ok(ResponseDto.setSuccess("교사 상태가 변경되었습니다.", responseDto));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<TeacherSimpleDto>>> getAllTeachers(
            @AuthenticationPrincipal String adminUsername) {
        List<TeacherSimpleDto> responseDto = teacherService.getAllTeachers(adminUsername);
        return ResponseEntity.ok(ResponseDto.setSuccess("교사 목록 조회 성공", responseDto));
    }
}