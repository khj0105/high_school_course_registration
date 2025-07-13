package com.example.high_school_course_registration.common.enums;

import lombok.Getter;

@Getter
public enum StudentStatus {
    PENDING("대기"),
    APPROVED("승인"),
    REJECTED("거절"),
    ENROLLED("재학"),
    GRADUATED("졸업");

    private final String description;

    StudentStatus(String description){
        this.description = description;
    }
}
