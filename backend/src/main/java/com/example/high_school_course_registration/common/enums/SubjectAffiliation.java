package com.example.high_school_course_registration.common.enums;

import lombok.Getter;

@Getter
public enum SubjectAffiliation {
    LIBERAL_ARTS("문과"),
    NATURAL_SCIENCES("이과"),
    COMMON("공통");

    private final String description;

    SubjectAffiliation(String description){
        this.description = description;
    }
}
