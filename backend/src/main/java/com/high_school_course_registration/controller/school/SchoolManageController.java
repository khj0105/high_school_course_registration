package com.high_school_course_registration.controller.school;

import com.high_school_course_registration.common.ApiMappingPattern;
import com.high_school_course_registration.dto.common.ResponseDto;
import com.high_school_course_registration.dto.school.request.RegistrationPeriodRequestDto;
import com.high_school_course_registration.service.SchoolManageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.SCHOOL_ADMIN) // /api/v2/school-admin
@RequiredArgsConstructor
public class SchoolManageController {

    private final SchoolManageService schoolManageService;

    @PutMapping("/registration-period")
    public ResponseEntity<ResponseDto<Void>> setRegistrationPeriod(
            @AuthenticationPrincipal String adminUsername,
            @Valid @RequestBody RegistrationPeriodRequestDto requestDto) {

        schoolManageService.setRegistrationPeriod(adminUsername, requestDto);
        return ResponseEntity.ok(ResponseDto.setSuccess("수강신청 기간이 설정되었습니다.", null));
    }
}