package com.high_school_course_registration.dto.course.response;

import com.high_school_course_registration.common.enums.DayOfWeek;
import com.high_school_course_registration.dto.schedule.ScheduleDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CourseDetailDto {
    private Long id;
    private String subjectName;
    private String description;
    private int maxEnrollment;
    private String teacherName;
    private DayOfWeek dayOfWeek;
    private int currentEnrollment;
    private String classroom;
    private List<ScheduleDto> schedules;
}
