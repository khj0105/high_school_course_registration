package com.high_school_course_registration.service;

import com.high_school_course_registration.dto.policy.request.PolicyUpdateRequestDto;
import com.high_school_course_registration.dto.policy.response.PolicyResponseDto;

public interface PolicyService {

    PolicyResponseDto getPolicy(String schoolCode);
    PolicyResponseDto updatePolicy(PolicyUpdateRequestDto requestDto, String schoolCode);
}