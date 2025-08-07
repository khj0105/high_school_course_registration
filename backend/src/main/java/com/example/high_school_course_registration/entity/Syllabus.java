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
    private String content;

    @Lob
    @Column(name = "learning_objectives")
    private String learningObjectives;

    @Lob
    @Column(name = "grading_policy")
    private String gradingPolicy;

    public Syllabus(Course course) {
        this.course = course;
    }

    public void update(String content, String objectives, String policy) {
        this.content = content;
        this.learningObjectives = objectives;
        this.gradingPolicy = policy;
    }
}