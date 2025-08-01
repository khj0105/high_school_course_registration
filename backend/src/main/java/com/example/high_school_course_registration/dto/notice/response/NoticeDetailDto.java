package com.example.high_school_course_registration.dto.notice.response;

import com.example.high_school_course_registration.common.enums.NoticeTargetAudience;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeDetailDto {
    private Long id;
    private String title;
    private String content;
    private String authorName;
    private NoticeTargetAudience targetAudience;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
