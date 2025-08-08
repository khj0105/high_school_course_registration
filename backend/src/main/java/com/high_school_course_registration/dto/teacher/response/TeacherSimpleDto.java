package com.high_school_course_registration.dto.teacher.response;

import com.high_school_course_registration.common.enums.TeacherStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherSimpleDto {
    private Long id;
    private String name;
    private String subject;
    private TeacherStatus teacherStatus;
}
