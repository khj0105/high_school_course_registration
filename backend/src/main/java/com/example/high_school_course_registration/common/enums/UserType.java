package com.example.high_school_course_registration.common.enums;

import lombok.Getter;

@Getter
public enum UserType {
    SUPER_ADMIN("최고 관리자"),
    SCHOOL_ADMIN("학교 관리자"),
    TEACHER("교사"),
    STUDENT("학생");

    private final String description;

    UserType(String description){
        this.description = description;
    }
}
