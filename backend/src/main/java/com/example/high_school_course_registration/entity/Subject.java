package com.example.high_school_course_registration.entity;

import com.example.high_school_course_registration.common.enums.SubjectAffiliation;
import com.example.high_school_course_registration.common.enums.SubjectStatus;
import com.example.high_school_course_registration.common.enums.SubjectType;
import com.example.high_school_course_registration.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subject")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Subject extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @Column(name = "subject_name", length = 50, nullable = false)
    private String subjectName;

    @Column(name = "subject_grade", nullable = false)
    private Long subjectGrade;

    @Column(name = "subject_semester", nullable = false)
    private Long subjectSemester;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject_type")
    private SubjectType subjectType;

    @Column(name = "subject_credits", nullable = false)
    private Long subjectCredits;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject_affiliation", nullable = false)
    private SubjectAffiliation subjectAffiliation;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject_status", nullable = false)
    private SubjectStatus subjectStatus;
}
