package com.example.high_school_course_registration.service.impl.auth;

import com.example.high_school_course_registration.common.enums.UserStatus;
import com.example.high_school_course_registration.common.exception.CustomException;
import com.example.high_school_course_registration.common.exception.ErrorCode;
import com.example.high_school_course_registration.dto.auth.request.LoginRequestDto;
import com.example.high_school_course_registration.dto.auth.response.StudentLoginResponseDto;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.entity.StudentDetails;
import com.example.high_school_course_registration.entity.User;
import com.example.high_school_course_registration.provider.JwtProvider;
import com.example.high_school_course_registration.repository.StudentDetailsRepository;
import com.example.high_school_course_registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentAuthServiceImpl {

    private final UserRepository userRepository;
    private final StudentDetailsRepository studentDetailsRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional(readOnly = true)
    public ResponseDto<StudentLoginResponseDto> login(LoginRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        if (user.getUserStatus() != UserStatus.ACTIVE) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }

        StudentDetails studentDetails = studentDetailsRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        String token = jwtProvider.generateJwtToken(user.getUsername(), user.getUserType().name());

        StudentLoginResponseDto responseData = StudentLoginResponseDto.builder()
                .studentId(user.getId())
                .studentName(user.getName())
                .studentEmail(user.getEmail())
                .studentGrade(studentDetails.getStudentGrade().intValue())
                .token(token)
                .build();

        return ResponseDto.setSuccess("학생 로그인 성공", responseData);
    }
}