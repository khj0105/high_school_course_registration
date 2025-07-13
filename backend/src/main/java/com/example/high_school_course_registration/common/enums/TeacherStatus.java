package com.example.high_school_course_registration.common.enums;

import lombok.Getter;

@Getter
public enum TeacherStatus {
    PENDING("대기"),
    APPROVED("승인"),
    ENROLLED("재직"),
    ON_LEAVE("휴직"),
    RETIRED("퇴직");

    private final String description;

    TeacherStatus(String description){
        this.description = description;
    }
}
