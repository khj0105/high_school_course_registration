package com.example.high_school_course_registration.dto.subject.response;

import com.example.high_school_course_registration.common.enums.SubjectAffiliation;
import com.example.high_school_course_registration.common.enums.SubjectStatus;
import com.example.high_school_course_registration.common.enums.SubjectType;
import lombok.*;


@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SubjectDetailDto {
    private Long id;
    private Long teacherId;
    private Long schoolId;
    private String subjectName;
    private Integer grade;
    private Integer semester;
    private SubjectType subjectType;
    private Integer credits;
    private SubjectAffiliation subjectAffiliation;
    private SubjectStatus status;
}
