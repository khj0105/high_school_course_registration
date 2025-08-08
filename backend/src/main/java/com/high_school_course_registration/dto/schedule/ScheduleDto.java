package com.high_school_course_registration.dto.schedule;

import com.high_school_course_registration.common.enums.DayOfWeek;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleDto {
    private Long courseId;
    private DayOfWeek dayOfWeek;
    private int period;
    private String subjectName;
    private String classroomName;
}
