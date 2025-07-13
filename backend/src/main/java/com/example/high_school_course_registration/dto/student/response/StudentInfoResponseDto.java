package com.example.high_school_course_registration.dto.student.response;

import com.example.high_school_course_registration.entity.Student;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class StudentInfoResponseDto {
    private String studentId;
    private String studentName;
    private String studentEmail;
    private String studentPhoneNumber;
    private LocalDate studentBirthDate;
    private String studentNumber;

    public static StudentInfoResponseDto from(Student student) {
        return StudentInfoResponseDto.builder()
                .studentId(student.getStudentId())
                .studentName(student.getStudentName())
                .studentEmail(student.getStudentEmail())
                .studentPhoneNumber(student.getStudentPhoneNumber())
                .studentBirthDate(student.getStudentBirthDate())
                .studentNumber(student.getStudentNumber())
                .build();
    }
}
