package com.example.high_school_course_registration.entity;

import com.example.high_school_course_registration.common.enums.SchoolApplicationStatus;
import com.example.high_school_course_registration.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "school_application")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SchoolApplication extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_application_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "school_application_status", nullable = false)
    private SchoolApplicationStatus schoolApplicationStatus;

    @Column(name = "school_name", length = 30, nullable = false)
    private String schoolName;

    @Column(name = "school_address", length = 255, nullable = false)
    private String schoolAddress;

    @Column(name = "school_email", length = 30, nullable = false)
    private String schoolEmail;

    @Column(name = "school_contact_number", length = 20, nullable = false)
    private String schoolContactNumber;

    @Column(name = "school_admin_name", length = 30, nullable = false)
    private String schoolAdminName;

    @Column(name = "school_admin_phone_number", length = 20, nullable = false)
    private String schoolAdminPhoneNumber;
}
