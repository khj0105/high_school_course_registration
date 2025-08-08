package com.high_school_course_registration.service.impl.auth;

import com.high_school_course_registration.common.enums.UserStatus;
import com.high_school_course_registration.common.exception.CustomException;
import com.high_school_course_registration.common.exception.ErrorCode;
import com.high_school_course_registration.dto.auth.request.LoginRequestDto;
import com.high_school_course_registration.dto.auth.response.TeacherLoginResponseDto;
import com.high_school_course_registration.dto.common.ResponseDto;
import com.high_school_course_registration.entity.TeacherDetails;
import com.high_school_course_registration.entity.User;
import com.high_school_course_registration.provider.JwtProvider;
import com.high_school_course_registration.repository.TeacherDetailsRepository;
import com.high_school_course_registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeacherAuthServiceImpl {

    private final UserRepository userRepository;
    private final TeacherDetailsRepository teacherDetailsRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional(readOnly = true)
    public ResponseDto<TeacherLoginResponseDto> login(LoginRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        if (user.getUserStatus() != UserStatus.ACTIVE) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }

        TeacherDetails teacherDetails = teacherDetailsRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        String token = jwtProvider.generateJwtToken(user.getUsername(), user.getUserType().name());

        TeacherLoginResponseDto responseData = TeacherLoginResponseDto.builder()
                .teacherId(user.getId())
                .teacherName(user.getName())
                .teacherEmail(user.getEmail())
                .teacherSubject(teacherDetails.getSubject())
                .token(token)
                .build();

        return ResponseDto.setSuccess("교사 로그인 성공", responseData);
    }
}