package com.example.high_school_course_registration.controller.registration;

import com.example.high_school_course_registration.common.ApiMappingPattern;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.registration.response.CourseEnrollmentSummaryDto;
import com.example.high_school_course_registration.service.CourseRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.MANAGEMENT_COURSES)
@RequiredArgsConstructor
public class CourseRegistrationController {

    private final CourseRegistrationService courseRegistrationService;

    @GetMapping("/{courseId}/enrolled-students")
    public ResponseEntity<ResponseDto<CourseEnrollmentSummaryDto>> getEnrolledStudents(
            @PathVariable Long courseId,
            @AuthenticationPrincipal String username) {
        CourseEnrollmentSummaryDto summary = courseRegistrationService.getEnrolledStudents(courseId, username);
        return ResponseEntity.ok(ResponseDto.setSuccess("수강생 목록 조회 성공", summary));
    }
}