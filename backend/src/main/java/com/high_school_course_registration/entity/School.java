package com.high_school_course_registration.entity;

import com.high_school_course_registration.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "school")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

public class School extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private Long id;

    @Column(name = "school_name", length = 30, nullable = false)
    private String schoolName;

    @Column(name = "school_address", length = 255, nullable = false)
    private String schoolAddress;

    @Column(name = "school_contact_number", length = 30, nullable = false)
    private String schoolContactNumber;

    @Column(name = "school_code", length = 30, nullable = false)
    private String schoolCode;

    @Column(name = "application_started_day", nullable = false)
    private LocalDate applicationStartedDay;

    @Column(name = "application_limited_day", nullable = false)
    private LocalDate applicationLimitedDay;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public void setRegistrationPeriod(LocalDate startDate, LocalDate endDate) {
        this.applicationStartedDay = startDate;
        this.applicationLimitedDay = endDate;
    }
}
