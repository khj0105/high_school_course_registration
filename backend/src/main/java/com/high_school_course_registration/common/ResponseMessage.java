package com.high_school_course_registration.common;

public class ResponseMessage {
    // === 공통(Common) ===
    public static final String SUCCESS = "Success";
    public static final String NO_AUTHORITY = "요청에 대한 권한이 없습니다.";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부에서 오류가 발생했습니다.";
    public static final String BAD_REQUEST = "잘못된 요청입니다.";

    // === 인증(Authentication) ===
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "아이디 또는 비밀번호가 일치하지 않습니다.";

    // === 학교(School) ===
    public static final String GET_SCHOOL_LIST_SUCCESS = "학교 목록 조회 성공";
    public static final String NOT_EXISTS_SCHOOL = "존재하지 않는 학교입니다.";

    // === 학생(Student) ===
    public static final String GET_STUDENT_LIST_SUCCESS = "학생 목록 조회 성공";
    public static final String GET_STUDENT_DETAIL_SUCCESS = "학생 상세 정보 조회 성공";
    public static final String NOT_EXISTS_STUDENT = "존재하지 않는 학생입니다.";

    // === 교사(Teacher) ===
    public static final String GET_TEACHER_LIST_SUCCESS = "교사 전체 목록 조회 성공";
    public static final String NOT_EXISTS_TEACHER = "존재하지 않는 교사입니다.";

    // === 과목(Subject) ===
    public static final String CREATE_SUBJECT_SUCCESS = "과목이 성공적으로 등록되었습니다.";
    public static final String UPDATE_SUBJECT_SUCCESS = "과목 정보가 성공적으로 수정되었습니다.";
    public static final String DELETE_SUBJECT_SUCCESS = "과목이 성공적으로 삭제되었습니다.";
    public static final String GET_SUBJECT_LIST_SUCCESS = "과목 목록 조회 성공";
    public static final String GET_SUBJECT_DETAIL_SUCCESS = "과목 상세 정보 조회 성공";
    public static final String UPDATE_SUBJECT_STATUS_SUCCESS = "과목 상태가 성공적으로 변경되었습니다.";
    public static final String APPROVE_SUBJECT_SUCCESS = "과목이 승인되어 강의가 생성되었습니다.";
    public static final String NOT_EXISTS_SUBJECT = "존재하지 않는 과목입니다.";
    public static final String ALREADY_EXISTS_SUBJECT = "이미 존재하는 과목입니다.";
    public static final String CANNOT_PROCESS_PENDING_ONLY = "'승인 대기' 상태의 과목만 처리할 수 있습니다.";

    // === 강의(Course) ===
    public static final String UPDATE_COURSE_SUCCESS = "강의 정보가 성공적으로 수정되었습니다.";
    public static final String DELETE_COURSE_SUCCESS = "강의가 성공적으로 삭제되었습니다.";
    public static final String GET_COURSE_LIST_SUCCESS = "강의 목록 조회 성공";
    public static final String GET_COURSE_DETAIL_SUCCESS = "강의 상세 정보 조회 성공";
    public static final String NOT_EXISTS_COURSE = "존재하지 않는 강의입니다.";
    public static final String CANNOT_DELETE_SUBJECT_HAS_COURSE = "해당 과목으로 개설된 강의가 있어 삭제할 수 없습니다.";

    // === 수강신청(Course Enrollment) ===
    public static final String ENROLL_COURSE_SUCCESS = "수강신청이 완료되었습니다.";
    public static final String CANCEL_ENROLLMENT_SUCCESS = "수강신청이 취소되었습니다.";
    public static final String GET_ENROLLED_STUDENTS_SUCCESS = "강의별 수강신청 학생 목록 조회 성공";
    public static final String GET_MY_ENROLLMENTS_SUCCESS = "나의 수강신청 내역 조회 성공";
    public static final String DUPLICATE_ENROLLMENT = "이미 수강신청한 과목입니다.";
    public static final String COURSE_CAPACITY_EXCEEDED = "수강 인원이 모두 찼습니다.";
}