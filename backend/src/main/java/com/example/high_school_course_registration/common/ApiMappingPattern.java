package com.example.high_school_course_registration.common;

public class ApiMappingPattern {
    private static final String API_V2 = "/api/v2";

    // === 공개 API (인증 불필요) ===
    public static final String PUBLIC = API_V2 + "/public";
    public static final String PUBLIC_SCHOOL_APPLICATIONS = PUBLIC + "/school-applications"; //학교 가입 신청
    public static final String PUBLIC_LOGIN = PUBLIC + "/login"; //로그인

    // === 슈퍼 관리자 API ===
    public static final String SUPER_ADMIN = API_V2 + "/super-admin";
    public static final String SUPER_ADMIN_SCHOOL_APPLICATIONS = SUPER_ADMIN + "/school-applications"; //학교 신청 승인/거절
    public static final String SUPER_ADMIN_SCHOOLS = SUPER_ADMIN + "/schools"; // 전체 학교 목록 조회

    // === 학교 관리자 API ===
    public static final String SCHOOL_ADMIN = API_V2 + "/school-admin";
    public static final String SCHOOL_ADMIN_TEACHERS = API_V2 + "/teachers"; //교사 계정 승인/관리
    public static final String SCHOOL_ADMIN_SUBJECTS = API_V2 + "/subjects"; // 과목 개설 승인/관리
    public static final String SCHOOL_ADMIN_POLICY = API_V2 + "/policy"; //학사 정책 관리

    // === 교사 API ===
    public static final String TEACHER = API_V2 + "/teacher";
    public static final String TEACHER_MY_SUBJECTS = TEACHER + "/my-subjects"; //등록 과목 조회/수정
    public static final String TEACHER_MY_COURSES = TEACHER + "/my-courses"; //담당 강의 조회

    // === 학생 API ===
    public static final String STUDENT = API_V2 + "/student";
    public static final String STUDENT_MY_ENROLLMENTS = STUDENT + "/my-enrollments"; // 수강신청 내역
    public static final String STUDENT_COURSESE_REGISTER = STUDENT + "/courses/register"; // 수강 신청

    // === 학교 관리자 + 교사 API ===
    public static final String MANAGEMENT = API_V2 + "/management";
    public static final String MANAGEMENT_STUDENTS = MANAGEMENT + "/students"; //학생 정보 조회
    public static final String MANAGEMENT_COURSES = MANAGEMENT + "/courses"; //전체 강의 조회/관리
    public static final String MANAGEMENT_COURSE_ENROLLED_STUDENT = MANAGEMENT + "/{courseId}/enrolled-students"; // 강의별 수강생 명단

    // === 교사 + 학생 API ===
    public static final String COMMON = API_V2 + "/common";
    public static final String COMMON_COURSES = COMMON + "/courses"; // 수강신청 가능 전체 강의 목록 조회
    public static final String COMMON_NOTICES = COMMON + "/notices"; // 공지사항 조회
}