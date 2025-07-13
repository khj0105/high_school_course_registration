package com.example.high_school_course_registration.common.enums;

import lombok.Getter;

@Getter
public enum SubjectType {
    REQUIRED_SUBJECT("필수 과목"),
    ELECTIVE_COURSES("선택 과목");

    private final String description;

    SubjectType(String description){
        this.description = description;
    }
}
