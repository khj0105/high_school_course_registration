package com.example.high_school_course_registration.dto.school.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class RegistrationPeriodRequestDto {
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
}