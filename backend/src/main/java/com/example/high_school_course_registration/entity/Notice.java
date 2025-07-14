package com.example.high_school_course_registration.entity;

import com.example.high_school_course_registration.common.enums.NoticeTargetAudience;
import com.example.high_school_course_registration.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "notice")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "notice_title", nullable = false)
    private String noticeTitle;

    @Lob
    @Column(name = "notice_content", nullable = false)
    private String noticeContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "notice_target_audience", nullable = false)
    private NoticeTargetAudience noticeTargetAudience;

    @Column(name = "notice_start_date", nullable = false)
    private LocalDate noticeStartDate;

    @Column(name = "notice_end_date", nullable = false)
    private LocalDate noticeEndDate;
}
