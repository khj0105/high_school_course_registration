package com.example.high_school_course_registration.controller;

import com.example.high_school_course_registration.common.ApiMappingPattern;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.school.response.SchoolSimpleDto;
import com.example.high_school_course_registration.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.API_COMMON)
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    // 모든 학교 목록 조회
    @GetMapping("/schools")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<ResponseDto<List<SchoolSimpleDto>>> getAllSchools() {
        ResponseDto<List<SchoolSimpleDto>> response = schoolService.getAllSchools();
        return ResponseEntity.ok(response);
    }
}