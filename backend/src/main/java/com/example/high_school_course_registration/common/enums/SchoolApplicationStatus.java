package com.example.high_school_course_registration.common.enums;

import lombok.Getter;

@Getter
public enum SchoolApplicationStatus {
    PENDING("승인 대기"),
    APPROVED("승인 완료"),
    REJECTED("승인 거절");

    private final String description;

    SchoolApplicationStatus(String description){
        this.description = description;
    }
}
