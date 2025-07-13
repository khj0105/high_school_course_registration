package com.example.high_school_course_registration.common.enums;

import lombok.Getter;

@Getter
public enum NoticeTargetAudience {
    ALL("전체"),
    STUDENT("학생"),
    TEACHER("교사");

    private final String description;

    NoticeTargetAudience(String description){
        this.description = description;
    }
}
