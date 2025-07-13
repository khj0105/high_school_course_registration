package com.example.high_school_course_registration.controller.auth;

import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.피드백_auth.request.LoginRequestDto;
import com.example.high_school_course_registration.dto.피드백_auth.response.TeacherLoginResponseDto;
import com.example.high_school_course_registration.service.impl.auth.TeacherAuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/teacher")
@RequiredArgsConstructor
public class TeacherAuthController {

    private final TeacherAuthServiceImpl teacherAuthService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<TeacherLoginResponseDto>> login(@RequestBody LoginRequestDto requestDto) {
        return teacherAuthService.login(requestDto);
    }
}
