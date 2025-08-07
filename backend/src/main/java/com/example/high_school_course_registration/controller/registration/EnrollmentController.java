package com.example.high_school_course_registration.controller.registration;

import com.example.high_school_course_registration.common.ApiMappingPattern;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.registration.request.EnrollmentRequestDto;
import com.example.high_school_course_registration.dto.registration.response.MyEnrollmentDto;
import com.example.high_school_course_registration.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.STUDENT_ENROLLMENTS) // /api/v2/student/enrollments
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<ResponseDto<MyEnrollmentDto>> createEnrollment(
            @AuthenticationPrincipal String username,
            @Valid @RequestBody EnrollmentRequestDto requestDto) {
        MyEnrollmentDto responseDto = enrollmentService.createEnrollment(username, requestDto);
        return ResponseEntity.ok(ResponseDto.setSuccess("수강신청이 완료되었습니다. 관리자 승인을 기다려주세요.", responseDto));
    }

    @GetMapping("/my")
    public ResponseEntity<ResponseDto<List<MyEnrollmentDto>>> getMyEnrollments(
            @AuthenticationPrincipal String username) {
        List<MyEnrollmentDto> responseDto = enrollmentService.getMyEnrollments(username);
        return ResponseEntity.ok(ResponseDto.setSuccess("나의 수강신청 내역 조회 성공", responseDto));
    }

    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<ResponseDto<Void>> cancelEnrollment(
            @AuthenticationPrincipal String username,
            @PathVariable Long enrollmentId) {
        enrollmentService.cancelEnrollment(username, enrollmentId);
        return ResponseEntity.ok(ResponseDto.setSuccess("수강신청이 취소되었습니다.", null));
    }
}