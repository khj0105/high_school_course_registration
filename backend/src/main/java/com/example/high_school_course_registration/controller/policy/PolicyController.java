package com.example.high_school_course_registration.controller.policy;

import com.example.high_school_course_registration.common.ApiMappingPattern;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.policy.request.PolicyUpdateRequestDto;
import com.example.high_school_course_registration.dto.policy.response.PolicyResponseDto;
import com.example.high_school_course_registration.service.PolicyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.SCHOOL_ADMIN_POLICY) // /api/v2/school-admin/policy
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    @GetMapping
    public ResponseEntity<ResponseDto<PolicyResponseDto>> getPolicy(
            @AuthenticationPrincipal String schoolCode) {
        PolicyResponseDto responseDto = policyService.getPolicy(schoolCode);
        return ResponseEntity.ok(ResponseDto.setSuccess("학사 정책 조회 성공", responseDto));
    }

    @PutMapping
    public ResponseEntity<ResponseDto<PolicyResponseDto>> updatePolicy(
            @Valid @RequestBody PolicyUpdateRequestDto requestDto,
            @AuthenticationPrincipal String schoolCode) {
        PolicyResponseDto responseDto = policyService.updatePolicy(requestDto, schoolCode);
        return ResponseEntity.ok(ResponseDto.setSuccess("학사 정책이 수정되었습니다.", responseDto));
    }
}