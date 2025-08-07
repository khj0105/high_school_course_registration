package com.example.high_school_course_registration.dto.school.response;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@Builder
public class SchoolDetailDto {
    private Long id;
    private String schoolName;
    private String schoolAddress;
    private String schoolContactNumber;
    private String schoolCode;
    private LocalDate applicationStartedDay;
    private LocalDate applicationLimitedDay;
}