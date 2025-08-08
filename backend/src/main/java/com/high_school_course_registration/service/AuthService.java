package com.high_school_course_registration.service;

import com.high_school_course_registration.dto.auth.request.LoginRequestDto;
import com.high_school_course_registration.dto.common.ResponseDto;
import com.high_school_course_registration.dto.school.request.SchoolApplicationRequestDto;
import com.high_school_course_registration.dto.student.request.StudentSignUpRequestDto;
import com.high_school_course_registration.dto.teacher.request.TeacherCreateRequestDto;

public interface AuthService {

    ResponseDto<?> login(String type, LoginRequestDto requestDto);

    void applyForSchool(SchoolApplicationRequestDto requestDto);
    void signupStudent(StudentSignUpRequestDto requestDto);
    void signupTeacher(TeacherCreateRequestDto requestDto);

    boolean isUsernameAvailable(String username);
    boolean isEmailAvailable(String email);

    void sendVerificationEmail(String email);
    void verifyEmailCode(String email, String code);
}