package com.example.high_school_course_registration.service.impl.student;

import com.example.high_school_course_registration.common.enums.StudentStatus;
import com.example.high_school_course_registration.common.enums.UserStatus;
import com.example.high_school_course_registration.common.enums.UserType;
import com.example.high_school_course_registration.common.exception.CustomException;
import com.example.high_school_course_registration.common.exception.ErrorCode;
import com.example.high_school_course_registration.dto.student.request.StudentUpdateRequestDto;
import com.example.high_school_course_registration.dto.student.response.StudentDetailDto;
import com.example.high_school_course_registration.dto.student.response.StudentSimpleDto;
import com.example.high_school_course_registration.entity.StudentDetails;
import com.example.high_school_course_registration.entity.User;
import com.example.high_school_course_registration.repository.StudentDetailsRepository;
import com.example.high_school_course_registration.repository.UserRepository;
import com.example.high_school_course_registration.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final UserRepository userRepository;
    private final StudentDetailsRepository studentDetailsRepository;

    @Override
    @Transactional(readOnly = true)
    public StudentDetailDto getStudentProfile(String username) {
        User user = findUserByUsername(username);
        StudentDetails studentDetails = findStudentDetailsByUser(user);
        return convertToDetailDto(studentDetails);
    }

    @Override
    @Transactional
    public StudentDetailDto updateStudentProfile(String username, StudentUpdateRequestDto requestDto) {
        User user = findUserByUsername(username);
        user.updateProfile(requestDto.getStudentName(), requestDto.getStudentPhoneNumber(), requestDto.getStudentEmail());

        StudentDetails studentDetails = findStudentDetailsByUser(user);
        return convertToDetailDto(studentDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDetailDto getStudentById(Long studentId) {
        StudentDetails studentDetails = studentDetailsRepository.findById(studentId)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));
        return convertToDetailDto(studentDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentSimpleDto> getAllStudents() {
        return studentDetailsRepository.findAll().stream()
                .map(this::convertToSimpleDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentSimpleDto> getPendingStudents(String adminUsername) {
        User admin = findUserByUsername(adminUsername);

        List<User> pendingStudents = userRepository.findBySchoolAndUserTypeAndUserStatus(
                admin.getSchool(), UserType.STUDENT, UserStatus.PENDING);

        return pendingStudents.stream()
                .map(user -> convertToSimpleDto(findStudentDetailsByUser(user)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StudentDetailDto updateStudentStatus(Long studentId, StudentStatus status, String adminUsername) {
        User admin = findUserByUsername(adminUsername);

        StudentDetails studentDetails = studentDetailsRepository.findById(studentId)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        if (studentDetails.getUser().getSchool() == null || !studentDetails.getUser().getSchool().equals(admin.getSchool())) {
            throw new CustomException(ErrorCode.STUDENT_MANAGE_FORBIDDEN);
        }

        studentDetails.updateStudentStatus(status);
        if (status == StudentStatus.APPROVED || status == StudentStatus.ENROLLED) {
            studentDetails.getUser().updateUserStatus(UserStatus.ACTIVE);
        } else {
            studentDetails.getUser().updateUserStatus(UserStatus.INACTIVE);
        }

        return convertToDetailDto(studentDetails);
    }

    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    private StudentDetails findStudentDetailsByUser(User user) {
        return studentDetailsRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_DETAILS_NOT_FOUND));
    }

    private StudentDetailDto convertToDetailDto(StudentDetails studentDetails) {
        User user = studentDetails.getUser();
        return StudentDetailDto.builder()
                .id(user.getId())
                .studentNumber(studentDetails.getStudentNumber())
                .studentName(user.getName())
                .studentGrade(studentDetails.getStudentGrade().intValue())
                .classroomName(studentDetails.getClassroom().getClassroomName())
                .studentEmail(user.getEmail())
                .studentPhoneNumber(user.getPhoneNumber())
                .studentBirthDate(studentDetails.getStudentBirthDate())
                .studentAdmissionYear(studentDetails.getStudentAdmissionYear())
                .studentStatus(studentDetails.getStudentStatus())
                .build();
    }

    private StudentSimpleDto convertToSimpleDto(StudentDetails studentDetails) {
        User user = studentDetails.getUser();
        return StudentSimpleDto.builder()
                .id(user.getId())
                .studentNumber(studentDetails.getStudentNumber())
                .studentName(user.getName())
                .studentGrade(studentDetails.getStudentGrade().intValue())
                .classroomName(studentDetails.getClassroom().getClassroomName())
                .build();
    }
}