package com.example.high_school_course_registration.service.impl.user;

import com.example.high_school_course_registration.common.exception.CustomException;
import com.example.high_school_course_registration.common.exception.ErrorCode;
import com.example.high_school_course_registration.dto.user.request.UserPasswordUpdateRequestDto;
import com.example.high_school_course_registration.entity.User;
import com.example.high_school_course_registration.repository.UserRepository;
import com.example.high_school_course_registration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void updatePassword(String username, UserPasswordUpdateRequestDto requestDto) {
        User user = findUserByUsername(username);


        if (!passwordEncoder.matches(requestDto.getCurrentPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }

        user.updatePassword(passwordEncoder.encode(requestDto.getNewPassword()));
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        User user = findUserByUsername(username);
        userRepository.delete(user);
    }

    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}