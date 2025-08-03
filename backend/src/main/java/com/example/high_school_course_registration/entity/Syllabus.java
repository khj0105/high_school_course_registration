package com.example.high_school_course_registration.entity;

import com.example.high_school_course_registration.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "syllabus")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Syllabus extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "syllabus_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false, unique = true)
    private Course course;

    @Lob
    @Column(name = "content")
    private String content; // 강의 개요

    @Lob
    @Column(name = "learning_objectives")
    private String learningObjectives; // 학습 목표

    @Lob
    @Column(name = "grading_policy")
    private String gradingPolicy; // 평가 기준
}