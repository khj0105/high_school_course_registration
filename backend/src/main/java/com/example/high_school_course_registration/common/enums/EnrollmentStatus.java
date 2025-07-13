package com.example.high_school_course_registration.common.enums;

import lombok.Getter;

@Getter
public enum EnrollmentStatus {
    ENROLLED("수강 선택"),
    COMPLETED("이수 완료"),
    WITHDRAWN("수강 취소");

    private final String description;

    EnrollmentStatus(String description){
        this.description = description;
    }
}
