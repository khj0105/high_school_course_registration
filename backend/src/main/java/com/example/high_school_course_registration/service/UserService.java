package com.example.high_school_course_registration.service;

import com.example.high_school_course_registration.dto.user.request.UserPasswordUpdateRequestDto;

public interface UserService {
    void updatePassword(String username, UserPasswordUpdateRequestDto requestDto);
    void deleteUser(String username);
}