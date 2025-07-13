package com.example.high_school_course_registration.common.enums;

import lombok.Getter;

@Getter
public enum SubjectStatus {
    APPROVED("개설 승인"),
    PENDING("개설 대기"),
    REJECTED("개설 거절");

    private final String description;

    SubjectStatus(String description){
        this.description = description;
    }
}
