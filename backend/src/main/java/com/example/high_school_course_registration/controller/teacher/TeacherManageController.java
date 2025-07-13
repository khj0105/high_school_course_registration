package com.example.high_school_course_registration.controller.teacher;

import com.example.high_school_course_registration.common.ApiMappingPattern;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.teacher.TeacherListGetResponseDto;
import com.example.high_school_course_registration.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.API_MANAGE_TEACHER)
@RequiredArgsConstructor
public class TeacherManageController {

    private final TeacherService teacherService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<List<TeacherListGetResponseDto>>> getTeacherList(
            @AuthenticationPrincipal String username
    ) {
        ResponseDto<List<TeacherListGetResponseDto>> response = teacherService.getTeacherList(username);
        return ResponseEntity.ok(response);
    }
}