package com.high_school_course_registration.controller.teacher;

import com.high_school_course_registration.common.ApiMappingPattern;
import com.high_school_course_registration.dto.common.ResponseDto;
import com.high_school_course_registration.dto.teacher.response.TeacherDetailDto;
import com.high_school_course_registration.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.TEACHER) // /api/v2/teacher
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/my-profile")
    public ResponseEntity<ResponseDto<TeacherDetailDto>> getMyProfile(
            @AuthenticationPrincipal String username) {
        TeacherDetailDto responseDto = teacherService.getTeacherProfile(username);
        return ResponseEntity.ok(ResponseDto.setSuccess("내 정보 조회 성공", responseDto));
    }
}