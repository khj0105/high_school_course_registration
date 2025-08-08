package com.high_school_course_registration.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 400 BAD_REQUEST: 잘못된 요청
    COURSE_CAPACITY_EXCEEDED(HttpStatus.BAD_REQUEST, "수강 인원이 모두 찼습니다."),
    INVALID_SEMESTER_FORMAT(HttpStatus.BAD_REQUEST, "잘못된 학기 정보입니다."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "현재 비밀번호가 일치하지 않습니다."),
    SUBJECT_UPDATE_INVALID_STATUS(HttpStatus.BAD_REQUEST, "'승인 대기' 상태의 과목만 수정할 수 있습니다."),
    ALREADY_PROCESSED_APPLICATION(HttpStatus.BAD_REQUEST, "이미 처리된 신청입니다."),
    SCHEDULE_REQUIRED_FOR_APPROVAL(HttpStatus.BAD_REQUEST, "강의를 승인하려면 시간표 정보가 반드시 필요합니다."),
    REGISTRATION_PERIOD_NOT_ACTIVE(HttpStatus.BAD_REQUEST, "수강신청 기간이 아닙니다."),
    CREDITS_EXCEEDED(HttpStatus.BAD_REQUEST, "학기당 최대 이수 학점을 초과합니다."),
    SCHEDULE_CONFLICT(HttpStatus.BAD_REQUEST, "이미 등록된 시간과 중복됩니다."),

    // 403 FORBIDDEN: 권한 없음
    INVALID_PERMISSION(HttpStatus.FORBIDDEN, "요청에 대한 접근 권한이 없습니다."),
    SUBJECT_CREATION_FORBIDDEN(HttpStatus.FORBIDDEN, "학교에 소속된 교사만 과목을 신청할 수 있습니다."),
    SUBJECT_UPDATE_FORBIDDEN(HttpStatus.FORBIDDEN, "자신이 신청한 과목만 수정할 수 있습니다."),
    SUBJECT_VIEW_FORBIDDEN(HttpStatus.FORBIDDEN, "자신이 신청한 과목만 조회할 수 있습니다."),
    SUBJECT_STATUS_UPDATE_FORBIDDEN(HttpStatus.FORBIDDEN, "소속 학교의 과목 상태만 변경할 수 있습니다."),
    SUBJECT_MANAGE_FORBIDDEN(HttpStatus.FORBIDDEN, "소속 학교의 과목만 관리할 수 있습니다."),
    SYLLABUS_PERMISSION_DENIED(HttpStatus.FORBIDDEN, "자신이 담당하는 강의의 계획서만 관리할 수 있습니다."),
    TEACHER_CREATION_FORBIDDEN(HttpStatus.FORBIDDEN, "학교 관리자만 교사 계정을 생성할 수 있습니다."),
    TEACHER_MANAGEMENT_FORBIDDEN(HttpStatus.FORBIDDEN, "소속 학교의 교사 정보만 관리할 수 있습니다."),
    NOTICE_CREATION_FORBIDDEN(HttpStatus.FORBIDDEN, "학교 소속 관리자만 공지사항을 작성할 수 있습니다."),
    NOTICE_UPDATE_FORBIDDEN(HttpStatus.FORBIDDEN, "해당 공지사항을 수정할 권한이 없습니다."),
    NOTICE_DELETE_FORBIDDEN(HttpStatus.FORBIDDEN, "해당 공지사항을 삭제할 권한이 없습니다."),
    STUDENT_MANAGE_FORBIDDEN(HttpStatus.FORBIDDEN, "소속 학교의 학생 정보만 변경할 수 있습니다."),
    ENROLLMENT_PERMISSION_DENIED(HttpStatus.FORBIDDEN, "자신의 수강신청 내역만 관리할 수 있습니다."),

    // 404 NOT_FOUND: 리소스를 찾을 수 없음
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 사용자를 찾을 수 없습니다."),
    TEACHER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 교사를 찾을 수 없습니다."),
    STUDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 학생을 찾을 수 없습니다."),
    COURSE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 강의를 찾을 수 없습니다."),
    SUBJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 과목을 찾을 수 없습니다."),
    SYLLABUS_NOT_FOUND(HttpStatus.NOT_FOUND, "강의 계획서가 아직 등록되지 않았습니다."),
    NOTICE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 공지사항을 찾을 수 없습니다."),
    CLASSROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 교실을 찾을 수 없습니다."),
    SCHOOL_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 학교를 찾을 수 없습니다."),
    POLICY_NOT_FOUND(HttpStatus.NOT_FOUND, "학사 정책이 설정되지 않았습니다."),
    APPLICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 신청을 찾을 수 없습니다."),
    ENROLLMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "수강신청 내역을 찾을 수 없습니다."),

    // 409 CONFLICT: 중복된 리소스
    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다."),
    DUPLICATE_ENROLLMENT(HttpStatus.CONFLICT, "이미 수강신청한 과목입니다."),

    // 500 INTERNAL_SERVER_ERROR: 서버 내부 오류
    STUDENT_DETAILS_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "학생의 상세 정보가 존재하지 않습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
