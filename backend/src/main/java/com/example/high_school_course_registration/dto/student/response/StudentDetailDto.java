package com.example.high_school_course_registration.dto.student.response;

import com.example.high_school_course_registration.common.enums.StudentStatus;
import com.example.high_school_course_registration.common.enums.SubjectAffiliation;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class StudentDetailDto {
    private Long id;
    private String studentNumber;
    private String studentName;
    private String classroomName;
    private int studentGrade;
    private String studentEmail;
    private String studentPhoneNumber;
    private LocalDate studentBirthDate;
    private int studentAdmissionYear;
    private StudentStatus studentStatus;
}
