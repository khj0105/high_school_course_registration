package com.example.high_school_course_registration.dto.subject.request;

import com.example.high_school_course_registration.common.enums.DayOfWeek;
import com.example.high_school_course_registration.common.enums.SubjectStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.util.List;

@Getter
public class SubjectApprovalRequestDto {
    @NotNull
    private SubjectStatus status;

    // status가 APPROVED일 경우에만 아래 값이 필요
    private List<ScheduleInfo> schedules;

    @Getter
    public static class ScheduleInfo {
        @NotNull
        private DayOfWeek dayOfWeek;
        @NotNull
        private Long period;
        @NotNull
        private Long classroomId;
    }
}