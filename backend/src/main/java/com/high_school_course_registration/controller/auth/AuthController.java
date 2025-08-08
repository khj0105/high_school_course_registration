package com.high_school_course_registration.controller.auth;

import com.high_school_course_registration.common.ApiMappingPattern;
import com.high_school_course_registration.dto.auth.request.EmailSendRequestDto;
import com.high_school_course_registration.dto.auth.request.EmailVerifyRequestDto;
import com.high_school_course_registration.dto.auth.request.LoginRequestDto;
import high_school_course_registration.high_school_course_registration.dto.auth.request.*;
import com.high_school_course_registration.dto.common.ResponseDto;
import com.high_school_course_registration.dto.school.request.SchoolApplicationRequestDto;
import com.high_school_course_registration.dto.student.request.StudentSignUpRequestDto;
import com.high_school_course_registration.dto.teacher.request.TeacherCreateRequestDto;
import com.high_school_course_registration.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.PUBLIC)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<?>> login(
            @RequestParam("type") String type,
            @Valid @RequestBody LoginRequestDto requestDto) {
        ResponseDto<?> response = authService.login(type, requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/school-applications")
    public ResponseEntity<ResponseDto<Void>> applyForSchool(
            @Valid @RequestBody SchoolApplicationRequestDto requestDto) {
        authService.applyForSchool(requestDto);
        return ResponseEntity.ok(ResponseDto.setSuccess("학교 가입 신청이 완료되었습니다.", null));
    }

    @PostMapping("/signup/student")
    public ResponseEntity<ResponseDto<Void>> signupStudent(
            @Valid @RequestBody StudentSignUpRequestDto requestDto) {
        authService.signupStudent(requestDto);
        return ResponseEntity.ok(ResponseDto.setSuccess("학생 회원가입이 완료되었습니다. 관리자 승인을 기다려주세요.", null));
    }

    @PostMapping("/signup/teacher")
    public ResponseEntity<ResponseDto<Void>> signupTeacher(
            @Valid @RequestBody TeacherCreateRequestDto requestDto) {
        authService.signupTeacher(requestDto);
        return ResponseEntity.ok(ResponseDto.setSuccess("교사 회원가입이 완료되었습니다. 관리자 승인을 기다려주세요.", null));
    }

    @GetMapping("/check-username")
    public ResponseEntity<ResponseDto<Boolean>> checkUsername(@RequestParam("username") String username) {
        boolean isAvailable = authService.isUsernameAvailable(username);
        return ResponseEntity.ok(ResponseDto.setSuccess("아이디 사용 가능 여부 확인", isAvailable));
    }

    @GetMapping("/check-email")
    public ResponseEntity<ResponseDto<Boolean>> checkEmail(@RequestParam("email") String email) {
        boolean isAvailable = authService.isEmailAvailable(email);
        return ResponseEntity.ok(ResponseDto.setSuccess("이메일 사용 가능 여부 확인", isAvailable));
    }

    @PostMapping("/emails/send-code")
    public ResponseEntity<ResponseDto<Void>> sendVerificationEmail(
            @Valid @RequestBody EmailSendRequestDto requestDto) {
        authService.sendVerificationEmail(requestDto.getEmail());
        return ResponseEntity.ok(ResponseDto.setSuccess("인증 코드가 이메일로 전송되었습니다.", null));
    }

    @PostMapping("/emails/verify-code")
    public ResponseEntity<ResponseDto<Void>> verifyEmailCode(
            @Valid @RequestBody EmailVerifyRequestDto requestDto) {
        authService.verifyEmailCode(requestDto.getEmail(), requestDto.getCode());
        return ResponseEntity.ok(ResponseDto.setSuccess("이메일 인증이 완료되었습니다.", null));
    }
}