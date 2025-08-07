package com.example.high_school_course_registration.controller.school; // 패키지 변경

import com.example.high_school_course_registration.common.ApiMappingPattern;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.school.response.SchoolSimpleDto;
import com.example.high_school_course_registration.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.PUBLIC_SCHOOLS)
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<SchoolSimpleDto>>> getAllSchools() {
        List<SchoolSimpleDto> schools = schoolService.getAllSchools();
        return ResponseEntity.ok(ResponseDto.setSuccess("학교 목록 조회 성공", schools));
    }
}