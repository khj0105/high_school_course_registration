package com.high_school_course_registration.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "classroom")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @Column(name = "classroom_name", length = 30, nullable = false)
    private String classroomName;

    @Column(name = "location_info", length = 100)
    private String locationInfo;

    public void updateClassroomInfo(String classroomName, String locationInfo) {
        this.classroomName = classroomName;
        this.locationInfo = locationInfo;
    }
}
