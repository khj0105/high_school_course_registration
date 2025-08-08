package com.high_school_course_registration.controller.classroom;

import com.high_school_course_registration.common.ApiMappingPattern;
import com.high_school_course_registration.dto.classroom.request.ClassroomRequestDto;
import com.high_school_course_registration.dto.classroom.response.ClassroomDto;
import com.high_school_course_registration.dto.common.ResponseDto;
import com.high_school_course_registration.service.ClassroomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.SCHOOL_ADMIN_CLASSROOMS)
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<ResponseDto<ClassroomDto>> createClassroom(
            @Valid @RequestBody ClassroomRequestDto requestDto,
            @AuthenticationPrincipal String schoolCode) {

        ClassroomDto responseDto = classroomService.createClassroom(requestDto, schoolCode);
        return ResponseEntity.ok(ResponseDto.setSuccess("교실이 성공적으로 생성되었습니다.", responseDto));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<ClassroomDto>>> getAllClassrooms(
            @AuthenticationPrincipal String schoolCode) {

        List<ClassroomDto> responseDtoList = classroomService.getAllClassrooms(schoolCode);
        return ResponseEntity.ok(ResponseDto.setSuccess("교실 목록 조회 성공", responseDtoList));
    }

    @PutMapping("/{classroomId}")
    public ResponseEntity<ResponseDto<ClassroomDto>> updateClassroom(
            @PathVariable Long classroomId,
            @Valid @RequestBody ClassroomRequestDto requestDto,
            @AuthenticationPrincipal String schoolCode) {

        ClassroomDto responseDto = classroomService.updateClassroom(classroomId, requestDto, schoolCode);
        return ResponseEntity.ok(ResponseDto.setSuccess("교실 정보가 수정되었습니다.", responseDto));
    }

    @DeleteMapping("/{classroomId}")
    public ResponseEntity<ResponseDto<Void>> deleteClassroom(
            @PathVariable Long classroomId,
            @AuthenticationPrincipal String schoolCode) {

        classroomService.deleteClassroom(classroomId, schoolCode);
        return ResponseEntity.ok(ResponseDto.setSuccess("교실이 삭제되었습니다.", null));
    }
}