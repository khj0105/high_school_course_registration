package com.high_school_course_registration.dto.policy.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PolicyResponseDto {
    private Integer maxCreditsSemester;
    private Integer graduationCredits;
}
