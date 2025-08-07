package com.example.high_school_course_registration.service.impl.teacher;

import com.example.high_school_course_registration.common.enums.TeacherStatus;
import com.example.high_school_course_registration.common.enums.UserStatus;
import com.example.high_school_course_registration.common.enums.UserType;
import com.example.high_school_course_registration.common.exception.CustomException;
import com.example.high_school_course_registration.common.exception.ErrorCode;
import com.example.high_school_course_registration.dto.teacher.request.TeacherCreateRequestDto;
import com.example.high_school_course_registration.dto.teacher.response.TeacherDetailDto;
import com.example.high_school_course_registration.dto.teacher.response.TeacherSimpleDto;
import com.example.high_school_course_registration.entity.School;
import com.example.high_school_course_registration.entity.TeacherDetails;
import com.example.high_school_course_registration.entity.User;
import com.example.high_school_course_registration.repository.TeacherDetailsRepository;
import com.example.high_school_course_registration.repository.UserRepository;
import com.example.high_school_course_registration.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final UserRepository userRepository;
    private final TeacherDetailsRepository teacherDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public TeacherDetailDto getTeacherProfile(String username) {
        User user = findUserByUsername(username);
        TeacherDetails teacherDetails = findTeacherDetailsByUser(user);
        return convertToDetailDto(teacherDetails);
    }

    @Override
    @Transactional
    public TeacherDetailDto createTeacher(TeacherCreateRequestDto requestDto, String adminUsername) {
        User admin = findUserByUsername(adminUsername);
        School school = admin.getSchool();
        if (school == null) {
            throw new CustomException(ErrorCode.TEACHER_CREATION_FORBIDDEN);
        }

        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        User teacherUser = User.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .email(requestDto.getEmail())
                .phoneNumber(requestDto.getPhoneNumber())
                .name(requestDto.getName())
                .userType(UserType.TEACHER)
                .school(school)
                .userStatus(UserStatus.PENDING)
                .build();
        User savedUser = userRepository.save(teacherUser);

        TeacherDetails teacherDetails = TeacherDetails.builder()
                .user(savedUser)
                .subject(requestDto.getSubject())
                .teacherStatus(TeacherStatus.PENDING)
                .build();

        teacherDetailsRepository.save(teacherDetails);
        return convertToDetailDto(teacherDetails);
    }

    @Override
    @Transactional
    public TeacherDetailDto updateTeacherStatus(Long teacherId, TeacherStatus status, String adminUsername) {
        User admin = findUserByUsername(adminUsername);
        TeacherDetails teacherDetails = findTeacherDetailsById(teacherId);

        if (!teacherDetails.getUser().getSchool().equals(admin.getSchool())) {
            throw new CustomException(ErrorCode.TEACHER_MANAGEMENT_FORBIDDEN);
        }

        teacherDetails.updateTeacherStatus(status);
        if (status == TeacherStatus.APPROVED) {
            teacherDetails.getUser().updateUserStatus(UserStatus.ACTIVE);
        } else {
            teacherDetails.getUser().updateUserStatus(UserStatus.INACTIVE);
        }

        return convertToDetailDto(teacherDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherSimpleDto> getAllTeachers(String adminUsername) {
        User admin = findUserByUsername(adminUsername);
        School school = admin.getSchool();

        List<User> teachers = userRepository.findBySchoolAndUserType(school, UserType.TEACHER);

        return teachers.stream()
                .map(user -> convertToSimpleDto(findTeacherDetailsByUser(user)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherSimpleDto> getPendingTeachers(String adminUsername) {
        User admin = findUserByUsername(adminUsername);
        School school = admin.getSchool();

        List<User> pendingTeachers = userRepository.findBySchoolAndUserTypeAndUserStatus(
                school, UserType.TEACHER, UserStatus.PENDING);

        return pendingTeachers.stream()
                .map(user -> convertToSimpleDto(findTeacherDetailsByUser(user)))
                .collect(Collectors.toList());
    }

    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    private TeacherDetails findTeacherDetailsById(Long teacherId) {
        return teacherDetailsRepository.findById(teacherId)
                .orElseThrow(() -> new CustomException(ErrorCode.TEACHER_NOT_FOUND));
    }

    private TeacherDetails findTeacherDetailsByUser(User user) {
        return findTeacherDetailsById(user.getId());
    }

    private TeacherDetailDto convertToDetailDto(TeacherDetails teacherDetails) {
        User user = teacherDetails.getUser();
        return TeacherDetailDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .subject(teacherDetails.getSubject())
                .teacherStatus(teacherDetails.getTeacherStatus())
                .build();
    }

    private TeacherSimpleDto convertToSimpleDto(TeacherDetails teacherDetails) {
        User user = teacherDetails.getUser();
        return TeacherSimpleDto.builder()
                .id(user.getId())
                .name(user.getName())
                .subject(teacherDetails.getSubject())
                .teacherStatus(teacherDetails.getTeacherStatus())
                .build();
    }
}