package com.example.high_school_course_registration.entity;

import com.example.high_school_course_registration.common.enums.ChangeStatus;
import com.example.high_school_course_registration.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admin_change_application")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AdminChangeApplication extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester; // 변경을 요청한 기존 관리자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_admin_id", nullable = false)
    private User newAdmin; // 새로운 관리자가 될 사용자

    @Lob
    @Column(name = "reason", nullable = false)
    private String reason; // 변경 사유

    @Enumerated(EnumType.STRING)
    @Column(name = "change_status", nullable = false)
    private ChangeStatus changeStatus; // PENDING, APPROVED, REJECTED
}