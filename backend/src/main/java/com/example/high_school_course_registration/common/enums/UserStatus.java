package com.example.high_school_course_registration.common.enums;

import lombok.Getter;

@Getter
public enum UserStatus {
    ACTIVE("활성"),
    INACTIVE("비활성"),
    PENDING("대기");

    private final String description;

    UserStatus(String description){
        this.description = description;
    }
}
