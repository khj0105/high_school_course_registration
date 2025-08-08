package com.high_school_course_registration.dto.subject.request;

import com.high_school_course_registration.common.enums.SubjectAffiliation;
import com.high_school_course_registration.common.enums.SubjectType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubjectUpdateRequestDto {
    @NotBlank
    private String subjectName;

    @NotNull @Min(1) @Max(3)
    private Integer grade;

    @NotNull @Min(1) @Max(2)
    private Integer semester;

    @NotNull
    private SubjectType subjectType;

    @NotNull @Min(1) @Max(3)
    private Integer credits;

    @NotNull
    private SubjectAffiliation subjectAffiliation;
}