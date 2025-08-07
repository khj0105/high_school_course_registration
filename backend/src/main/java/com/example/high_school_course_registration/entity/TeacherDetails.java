package com.example.high_school_course_registration.entity;

import com.example.high_school_course_registration.common.enums.TeacherStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "teacher_details")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class TeacherDetails {

    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "subject", length = 10, nullable = false)
    private String subject;

    @Enumerated(EnumType.STRING)
    @Column(name = "teacher_status", nullable = false)
    private TeacherStatus teacherStatus;

    public void updateTeacherStatus(TeacherStatus status) {
        this.teacherStatus = status;
    }
}
