package com.high_school_course_registration.controller.course;

import com.high_school_course_registration.common.ApiMappingPattern;
import com.high_school_course_registration.dto.common.ResponseDto;
import com.high_school_course_registration.dto.course.request.CourseCreateRequestDto;
import com.high_school_course_registration.dto.course.request.CourseUpdateRequestDto;
import com.high_school_course_registration.dto.course.response.CourseDetailDto;
import com.high_school_course_registration.dto.course.response.CourseSimpleDto;
import com.high_school_course_registration.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.MANAGEMENT_COURSES)
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<ResponseDto<CourseDetailDto>> createCourse(
            @Valid @RequestBody CourseCreateRequestDto requestDto,
            @AuthenticationPrincipal String username) {
        CourseDetailDto createdCourse = courseService.createCourse(requestDto, username);
        return ResponseEntity.ok(ResponseDto.setSuccess("강의가 성공적으로 생성되었습니다.", createdCourse));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<CourseSimpleDto>>> getAllCourses(
            @AuthenticationPrincipal String username) {
        List<CourseSimpleDto> courses = courseService.getAllCourses(username);
        return ResponseEntity.ok(ResponseDto.setSuccess("강의 목록 조회 성공", courses));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<ResponseDto<CourseDetailDto>> getCourseById(
            @PathVariable Long courseId,
            @AuthenticationPrincipal String username) {
        CourseDetailDto course = courseService.getCourseById(courseId, username);
        return ResponseEntity.ok(ResponseDto.setSuccess("강의 상세 정보 조회 성공", course));
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<ResponseDto<CourseDetailDto>> updateCourse(
            @PathVariable Long courseId,
            @Valid @RequestBody CourseUpdateRequestDto requestDto,
            @AuthenticationPrincipal String username) {
        CourseDetailDto updatedCourse = courseService.updateCourse(courseId, requestDto, username);
        return ResponseEntity.ok(ResponseDto.setSuccess("강의 정보가 수정되었습니다.", updatedCourse));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<ResponseDto<Void>> deleteCourse(
            @PathVariable Long courseId,
            @AuthenticationPrincipal String username) {
        courseService.deleteCourse(courseId, username);
        return ResponseEntity.ok(ResponseDto.setSuccess("강의가 삭제되었습니다.", null));
    }
}