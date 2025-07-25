package com.example.high_school_course_registration.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 400 BAD_REQUEST: 잘못된 요청
    COURSE_CAPACITY_EXCEEDED(HttpStatus.BAD_REQUEST, "수강 인원이 모두 찼습니다."),
    INVALID_SEMESTER_FORMAT(HttpStatus.BAD_REQUEST, "잘못된 학기 정보입니다."),

    // 403 FORBIDDEN: 권한 없음
    INVALID_PERMISSION(HttpStatus.BAD_REQUEST, "요청에 대한 접근 권한이 없습니다."),

    // 404 NOT_FOUND: 리소스를 찾을 수 없음
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 사용자를 찾을 수 없습니다."),
    COURSE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 강의를 찾을 수 없습니다."),
    SUBJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 과목을 찾을 수 없습니다."),

    // 409 CONFLICT: 중복된 리소스
    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
    DUPLICATE_ENROLLMENT(HttpStatus.CONFLICT, "이미 수강신청한 과목입니다."),

    // 500 INTERNAL_SERVER_ERROR: 서버 내부 오류
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
