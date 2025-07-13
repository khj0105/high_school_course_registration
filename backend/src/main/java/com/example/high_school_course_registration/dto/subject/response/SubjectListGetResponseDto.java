package com.example.high_school_course_registration.dto.subject.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectListGetResponseDto {
    private String subjectId;
    private String subjectName;
    private String grade;
    private String semester;
    private SubjectAffiliation affiliation;
}