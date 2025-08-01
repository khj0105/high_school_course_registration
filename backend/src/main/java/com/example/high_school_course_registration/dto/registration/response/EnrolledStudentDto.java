package com.example.high_school_course_registration.dto.registration.response;

import com.example.high_school_course_registration.common.enums.EnrollmentApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrolledStudentDto {
    private Long studentUserId;
    private String studentNumber;
    private String studentName;
    private int studentGrade;
    private int studentClass;
    private EnrollmentApprovalStatus status;
}