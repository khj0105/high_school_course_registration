package com.example.high_school_course_registration.entity;

import com.example.high_school_course_registration.common.enums.StudentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "student_details")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class StudentDetails {

    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "student_number", length = 30, nullable = false, unique = true)
    private String studentNumber;

    @Column(name = "student_grade", nullable = false)
    private Long studentGrade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @Column(name = "student_birth_date", nullable = false)
    private LocalDate studentBirthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "student_status", nullable = false)
    private StudentStatus studentStatus;

    @Column(name = "student_admission_year", nullable = false)
    private int studentAdmissionYear;
}
