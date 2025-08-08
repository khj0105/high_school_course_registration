package com.high_school_course_registration.dto.registration.response;

import com.high_school_course_registration.common.enums.DayOfWeek;
import com.high_school_course_registration.common.enums.EnrollmentApprovalStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyEnrollmentDto {
    private Long enrollmentId;
    private Long courseId;
    private String subjectName;
    private String teacherName;
    private DayOfWeek dayOfWeek;
    private int period;
    private EnrollmentApprovalStatus approvalStatus;
}
