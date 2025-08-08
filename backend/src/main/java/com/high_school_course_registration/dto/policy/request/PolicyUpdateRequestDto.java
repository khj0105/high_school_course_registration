package com.high_school_course_registration.dto.policy.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PolicyUpdateRequestDto {
    @NotNull
    @Min(1)
    private Integer maxCreditsSemester;

    @NotNull
    @Min(1)
    private Integer graduationCredits;
}
