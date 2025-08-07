package com.example.high_school_course_registration.controller.student;

import com.example.high_school_course_registration.common.ApiMappingPattern;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.student.request.StudentStatusUpdateRequestDto;
import com.example.high_school_course_registration.dto.student.response.StudentDetailDto;
import com.example.high_school_course_registration.dto.student.response.StudentSimpleDto;
import com.example.high_school_course_registration.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.MANAGEMENT_STUDENTS) // /api/v2/management/students
@RequiredArgsConstructor
public class StudentManageController {

    private final StudentService studentService;

    @GetMapping("/pending")
    public ResponseEntity<ResponseDto<List<StudentSimpleDto>>> getPendingStudents(
            @AuthenticationPrincipal String adminUsername) {
        List<StudentSimpleDto> responseDto = studentService.getPendingStudents(adminUsername);
        return ResponseEntity.ok(ResponseDto.setSuccess("승인 대기 학생 목록 조회 성공", responseDto));
    }

    @PutMapping("/{studentId}/status")
    public ResponseEntity<ResponseDto<StudentDetailDto>> updateStudentStatus(
            @PathVariable Long studentId,
            @Valid @RequestBody StudentStatusUpdateRequestDto requestDto,
            @AuthenticationPrincipal String adminUsername) {
        StudentDetailDto responseDto = studentService.updateStudentStatus(studentId, requestDto.getStatus(), adminUsername);
        return ResponseEntity.ok(ResponseDto.setSuccess("학생 상태가 변경되었습니다.", responseDto));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<ResponseDto<StudentDetailDto>> getStudentById(@PathVariable Long studentId) {
        StudentDetailDto responseDto = studentService.getStudentById(studentId);
        return ResponseEntity.ok(ResponseDto.setSuccess("학생 상세 정보 조회 성공", responseDto));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<StudentSimpleDto>>> getAllStudents() {
        // To-Do: 검색 조건 파라미터 추가
        List<StudentSimpleDto> responseDto = studentService.getAllStudents();
        return ResponseEntity.ok(ResponseDto.setSuccess("학생 목록 조회 성공", responseDto));
    }
}