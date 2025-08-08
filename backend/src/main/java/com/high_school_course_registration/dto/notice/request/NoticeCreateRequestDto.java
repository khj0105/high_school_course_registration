package com.high_school_course_registration.dto.notice.request;

import com.high_school_course_registration.common.enums.NoticeTargetAudience;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class NoticeCreateRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private NoticeTargetAudience targetAudience;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
}
