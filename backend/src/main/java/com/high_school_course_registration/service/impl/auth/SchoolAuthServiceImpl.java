package com.high_school_course_registration.service.impl.auth;

import com.high_school_course_registration.common.enums.UserType;
import com.high_school_course_registration.common.exception.CustomException;
import com.high_school_course_registration.common.exception.ErrorCode;
import com.high_school_course_registration.dto.auth.request.LoginRequestDto;
import com.high_school_course_registration.dto.auth.response.SchoolLoginResponseDto;
import com.high_school_course_registration.dto.common.ResponseDto;
import com.high_school_course_registration.entity.School;
import com.high_school_course_registration.entity.User;
import com.high_school_course_registration.provider.JwtProvider;
import com.high_school_course_registration.repository.SchoolRepository;
import com.high_school_course_registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SchoolAuthServiceImpl {

    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional(readOnly = true)
    public ResponseDto<SchoolLoginResponseDto> login(LoginRequestDto requestDto) {
        School school = schoolRepository.findBySchoolCodeAndDeletedAtIsNull(requestDto.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        User admin = userRepository.findBySchoolAndUserType(school, UserType.SCHOOL_ADMIN).stream().findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(requestDto.getPassword(), admin.getPassword())) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        String token = jwtProvider.generateJwtToken(admin.getUsername(), admin.getUserType().name());

        SchoolLoginResponseDto responseData = SchoolLoginResponseDto.builder()
                .schoolId(school.getId())
                .schoolCode(school.getSchoolCode())
                .schoolName(school.getSchoolName())
                .schoolAdminName(admin.getName())
                .token(token)
                .build();

        return ResponseDto.setSuccess("학교 관리자 로그인 성공", responseData);
    }
}