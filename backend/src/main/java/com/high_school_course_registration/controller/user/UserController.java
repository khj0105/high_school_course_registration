package com.high_school_course_registration.controller.user;

import com.high_school_course_registration.common.ApiMappingPattern;
import com.high_school_course_registration.dto.common.ResponseDto;
import com.high_school_course_registration.dto.user.request.UserPasswordUpdateRequestDto;
import com.high_school_course_registration.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.AUTHENTICATED_USER) // /api/v2/user
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/password")
    public ResponseEntity<ResponseDto<Void>> updatePassword(
            @AuthenticationPrincipal String username,
            @Valid @RequestBody UserPasswordUpdateRequestDto requestDto) {

        userService.updatePassword(username, requestDto);
        return ResponseEntity.ok(ResponseDto.setSuccess("비밀번호가 성공적으로 변경되었습니다.", null));
    }


    @DeleteMapping("/me")
    public ResponseEntity<ResponseDto<Void>> deleteUser(
            @AuthenticationPrincipal String username) {

        userService.deleteUser(username);
        return ResponseEntity.ok(ResponseDto.setSuccess("회원 탈퇴가 완료되었습니다.", null));
    }
}