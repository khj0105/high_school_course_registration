package com.example.high_school_course_registration.dto.schedule.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleResponseDto {
    private String dayOfWeek;
    private int period;
    private String subjectName;
    private String lectureRoom;
}
