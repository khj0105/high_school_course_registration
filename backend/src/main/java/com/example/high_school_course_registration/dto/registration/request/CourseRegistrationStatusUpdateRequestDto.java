package com.example.high_school_course_registration.dto.registration.request;

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
    @NotNull(message = "수강신청 상태는 필수입니다.")
    private CourseRegistrationStatus status;
}