package com.example.high_school_course_registration.controller.auth;

import com.example.high_school_course_registration.dto.auth.request.LoginRequestDto;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.피드백_auth.response.SchoolLoginResponseDto;
import com.example.high_school_course_registration.service.impl.auth.SchoolAuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/school")
@RequiredArgsConstructor
public class SchoolAuthController {

    private final SchoolAuthServiceImpl schoolAuthService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<SchoolLoginResponseDto>> login(@RequestBody LoginRequestDto requestDto) {
        return schoolAuthService.login(requestDto);
    }
}