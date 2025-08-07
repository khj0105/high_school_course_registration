package com.example.high_school_course_registration.dto.registration.request;

import com.example.high_school_course_registration.common.enums.EnrollmentApprovalStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistrationStatusUpdateRequestDto {
    @NotNull
    private EnrollmentApprovalStatus status;
}