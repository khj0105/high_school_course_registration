package com.example.high_school_course_registration.dto.subject.response;

import lombok.*;


@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SubjectGetResponseDto {
    private String subjectId;
    private Long schoolId;
    private String subjectName;
    private String grade;
    private String semester;
    private SubjectAffiliation affiliation;
    private SubjectStatus status;
    private Integer maxEnrollment;
}
