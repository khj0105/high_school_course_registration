package com.example.high_school_course_registration.entity;

import com.example.high_school_course_registration.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "school_policy")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SchoolPolicy extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", unique = true, nullable = false)
    private School school;

    @Column(name = "max_credits_semester", nullable = false)
    private Long maxCreditsSemester;

    @Column(name = "graduation_credits", nullable = false)
    private Long graduationCredits;
}
