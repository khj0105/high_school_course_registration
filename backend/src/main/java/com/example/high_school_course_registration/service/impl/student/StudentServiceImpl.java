package com.example.high_school_course_registration.service.impl.student;

import com.example.high_school_course_registration.common.ResponseMessage;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.student.response.StudentDetailDto;
import com.example.high_school_course_registration.dto.student.response.StudentSimpleDto;
import com.example.high_school_course_registration.service.StudentManageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentManageServiceImpl implements StudentManageService {

    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public ResponseDto<StudentDetailDto> findByStudentId(String username, String studentId) {
        StudentDetailDto responseData = null;

        boolean isAuthorized = schoolRepository.existsBySchoolCode(username)
                || teacherRepository.existsByTeacherUsername(username);

        if (!isAuthorized) {
            throw new RuntimeException(ResponseMessage.NO_AUTHORITY + ": " + username);
        }

        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_STUDENT + ": " + studentId));

        responseData = StudentDetailDto.builder()
                .studentId(student.getStudentId())
                .studentNumber(student.getStudentNumber())
                .studentName(student.getStudentName())
                .studentClass(student.getStudentClass())
                .studentGrade(student.getStudentGrade())
                .studentEmail(student.getStudentEmail())
                .studentPhoneNumber(student.getStudentPhoneNumber())
                .studentBirthDate(student.getStudentBirthDate())
                .studentAffiliation(student.getStudentAffiliation())
                .studentAdmissionYear(student.getStudentAdmissionYear())
                .studentStatus(student.getStudentStatus())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.GET_STUDENT_DETAIL_SUCCESS, responseData);
    }

    @Override
    public ResponseDto<List<StudentSimpleDto>> searchStudents(String username, String studentNumber, String studentName, int studentGrade, int studentClass, SubjectAffiliation studentAffiliation) {


        boolean isAuthorized = schoolRepository.existsBySchoolCode(username)
                || teacherRepository.existsByTeacherUsername(username);

        if (!isAuthorized) {
            throw new RuntimeException(ResponseMessage.NO_AUTHORITY + ": " + username);
        }

        List<Student> students = studentRepository.searchStudents(studentNumber, studentName, studentGrade, studentClass, studentAffiliation);

        List<StudentSimpleDto> responseData = students.stream()
                .map(student -> StudentSimpleDto.builder()
                        .studentNumber(student.getStudentNumber())
                        .studentName(student.getStudentName())
                        .studentGrade(student.getStudentGrade())
                        .studentClass(student.getStudentClass())
                        .studentEmail(student.getStudentEmail())
                        .studentAffiliation(student.getStudentAffiliation())
                        .build())
                .collect(Collectors.toList());
        return ResponseDto.setSuccess(ResponseMessage.GET_STUDENT_LIST_SUCCESS, responseData);
    }
}
