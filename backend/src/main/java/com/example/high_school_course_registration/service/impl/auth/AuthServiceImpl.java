package com.example.high_school_course_registration.service.impl.auth;

import com.example.high_school_course_registration.common.enums.*;
import com.example.high_school_course_registration.common.exception.CustomException;
import com.example.high_school_course_registration.common.exception.ErrorCode;
import com.example.high_school_course_registration.dto.auth.request.LoginRequestDto;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.school.request.SchoolApplicationRequestDto;
import com.example.high_school_course_registration.dto.student.request.StudentSignUpRequestDto;
import com.example.high_school_course_registration.dto.teacher.request.TeacherCreateRequestDto;
import com.example.high_school_course_registration.entity.*;
import com.example.high_school_course_registration.repository.*;
import com.example.high_school_course_registration.service.AuthService;
import com.example.high_school_course_registration.service.EmailAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final SchoolAuthServiceImpl schoolAuthService;
    private final StudentAuthServiceImpl studentAuthService;
    private final TeacherAuthServiceImpl teacherAuthService;
    private final EmailAuthService emailAuthService;
    private final UserRepository userRepository;
    private final SchoolApplicationRepository schoolApplicationRepository;
    private final StudentDetailsRepository studentDetailsRepository;
    private final TeacherDetailsRepository teacherDetailsRepository;
    private final ClassroomRepository classroomRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseDto<?> login(String type, LoginRequestDto requestDto) {
        return switch (type.toLowerCase()) {
            case "school_admin" -> schoolAuthService.login(requestDto);
            case "student" -> studentAuthService.login(requestDto);
            case "teacher" -> teacherAuthService.login(requestDto);
            default -> throw new CustomException(ErrorCode.USER_NOT_FOUND);
        };
    }

    @Override
    @Transactional
    public void applyForSchool(SchoolApplicationRequestDto requestDto) {
        if (schoolApplicationRepository.existsBySchoolEmail(requestDto.getSchoolEmail())) {
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }

        SchoolApplication application = SchoolApplication.builder()
                .schoolName(requestDto.getSchoolName())
                .schoolAddress(requestDto.getSchoolAddress())
                .schoolEmail(requestDto.getSchoolEmail())
                .schoolContactNumber(requestDto.getSchoolContactNumber())
                .schoolAdminName(requestDto.getSchoolAdminName())
                .schoolAdminPhoneNumber(requestDto.getSchoolAdminPhoneNumber())
                .schoolApplicationStatus(SchoolApplicationStatus.PENDING)
                .build();

        schoolApplicationRepository.save(application);
    }

    @Override
    @Transactional
    public void signupStudent(StudentSignUpRequestDto requestDto) {
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }
        if (userRepository.existsByEmail(requestDto.getStudentEmail())) {
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }

        Classroom classroom = classroomRepository.findById(requestDto.getClassroomId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        User user = User.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .email(requestDto.getStudentEmail())
                .phoneNumber(requestDto.getStudentPhoneNumber())
                .name(requestDto.getStudentName())
                .userType(UserType.STUDENT)
                .school(classroom.getSchool())
                .userStatus(UserStatus.PENDING)
                .build();
        User savedUser = userRepository.save(user);

        StudentDetails studentDetails = StudentDetails.builder()
                .user(savedUser)
                .studentNumber(requestDto.getStudentNumber())
                .studentGrade(Long.valueOf(requestDto.getStudentGrade()))
                .classroom(classroom)
                .studentBirthDate(requestDto.getStudentBirthDate())
                .studentStatus(StudentStatus.PENDING)
                .studentAdmissionYear(requestDto.getStudentAdmissionYear())
                .build();

        studentDetailsRepository.save(studentDetails);
    }

    @Override
    @Transactional
    public void signupTeacher(TeacherCreateRequestDto requestDto) {
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }

        User user = User.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .email(requestDto.getEmail())
                .phoneNumber(requestDto.getPhoneNumber())
                .name(requestDto.getName())
                .userType(UserType.TEACHER)
                .userStatus(UserStatus.PENDING)
                .build();
        User savedUser = userRepository.save(user);

        TeacherDetails teacherDetails = TeacherDetails.builder()
                .user(savedUser)
                .subject(requestDto.getSubject())
                .teacherStatus(TeacherStatus.PENDING)
                .build();

        teacherDetailsRepository.save(teacherDetails);
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return !userRepository.existsByEmail(email);
    }

    @Override
    public void sendVerificationEmail(String email) {
        emailAuthService.sendVerificationEmail(email);
    }

    @Override
    public void verifyEmailCode(String email, String code) {
        emailAuthService.verifyEmailCode(email, code);
    }
}