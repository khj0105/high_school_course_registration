package com.example.high_school_course_registration.dto.subject.response;

import com.example.high_school_course_registration.common.enums.SubjectAffiliation;
import com.example.high_school_course_registration.common.enums.SubjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectSimpleDto {
    private Long id;
    private String subjectName;
    private String teacherName;
    private Integer grade;
    private Integer semester;
    private SubjectAffiliation subjectAffiliation;
    private SubjectStatus status;
}