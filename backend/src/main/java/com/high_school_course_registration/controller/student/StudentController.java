package com.high_school_course_registration.controller.student;

import com.high_school_course_registration.common.ApiMappingPattern;
import com.high_school_course_registration.dto.common.ResponseDto;
import com.high_school_course_registration.dto.student.request.StudentUpdateRequestDto;
import com.high_school_course_registration.dto.student.response.StudentDetailDto;
import com.high_school_course_registration.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.STUDENT) // /api/v2/student
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/my-profile")
    public ResponseEntity<ResponseDto<StudentDetailDto>> getMyProfile(
            @AuthenticationPrincipal String username) {
        StudentDetailDto responseDto = studentService.getStudentProfile(username);
        return ResponseEntity.ok(ResponseDto.setSuccess("내 정보 조회 성공", responseDto));
    }

    @PutMapping("/my-profile")
    public ResponseEntity<ResponseDto<StudentDetailDto>> updateMyProfile(
            @AuthenticationPrincipal String username,
            @Valid @RequestBody StudentUpdateRequestDto requestDto) {
        StudentDetailDto responseDto = studentService.updateStudentProfile(username, requestDto);
        return ResponseEntity.ok(ResponseDto.setSuccess("내 정보가 수정되었습니다.", responseDto));
    }
}