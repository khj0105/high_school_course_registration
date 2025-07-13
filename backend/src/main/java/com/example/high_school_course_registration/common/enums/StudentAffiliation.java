package com.example.high_school_course_registration.common.enums;

import lombok.Getter;

@Getter
public enum StudentAffiliation {
    LIBERAL_ARTS("문과"),
    NATURAL_SCIENCES("이과");

    private final String description;

    StudentAffiliation(String description){
        this.description = description;
    }
}
