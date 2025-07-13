package com.example.high_school_course_registration.common.enums;

import lombok.Getter;

@Getter
public enum EnrollmentApprovalStatus {
    PENDING("수강신청 대기"),
    APPROVED("수강신청 확정"),
    REJECTED("수강신청 거절");

    private final String description;

    EnrollmentApprovalStatus(String description){
        this.description = description;
    }
}
