package com.example.high_school_course_registration.dto.notice.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeSimpleDto {
    private Long id;
    private String title;
    private String authorName;
    private LocalDateTime createdAt;
}
