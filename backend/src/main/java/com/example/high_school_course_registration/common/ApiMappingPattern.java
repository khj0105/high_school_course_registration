package com.example.high_school_course_registration.common;

public class ApiMappingPattern {
    private static final String API_V2 = "/api/v2";

    // === 공개 API (인증 불필요) ===
    public static final String PUBLIC = API_V2 + "/public";
    public static final String PUBLIC_SCHOOL_APPLICATIONS = PUBLIC + "/school-applications";
    public static final String PUBLIC_LOGIN = PUBLIC + "/login";
    public static final String PUBLIC_LOGOUT = PUBLIC + "/logout";
    public static final String PUBLIC_SCHOOLS = PUBLIC + "/schools";

    // === 슈퍼 관리자 API ===
    public static final String SUPER_ADMIN = API_V2 + "/super-admin";
    public static final String SUPER_ADMIN_SCHOOL_APPLICATIONS = SUPER_ADMIN + "/school-applications";
    public static final String SUPER_ADMIN_SCHOOLS = SUPER_ADMIN + "/schools";

    // === 학교 관리자 API ===
    public static final String SCHOOL_ADMIN = API_V2 + "/school-admin";
    public static final String SCHOOL_ADMIN_TEACHERS = SCHOOL_ADMIN + "/teachers";
    public static final String SCHOOL_ADMIN_SUBJECTS = SCHOOL_ADMIN + "/subjects";
    public static final String SCHOOL_ADMIN_POLICY = SCHOOL_ADMIN + "/policy";
    public static final String SCHOOL_ADMIN_USERS = SCHOOL_ADMIN + "/users";
    public static final String SCHOOL_ADMIN_NOTICES = SCHOOL_ADMIN + "/notices";
    public static final String SCHOOL_ADMIN_CLASSROOMS = SCHOOL_ADMIN + "/classrooms";

    // === 교사 API ===
    public static final String TEACHER = API_V2 + "/teacher";
    public static final String TEACHER_SUBJECTS = TEACHER + "/subjects";
    public static final String TEACHER_MY_SUBJECTS = TEACHER + "/my-subjects";
    public static final String TEACHER_MY_COURSES = TEACHER + "/my-courses";

    // === 학생 API ===
    public static final String STUDENT = API_V2 + "/student";
    public static final String STUDENT_MY_ENROLLMENTS = STUDENT + "/my-enrollments";
    public static final String STUDENT_COURSES_REGISTER = STUDENT + "/courses/register";
    public static final String STUDENT_ENROLLMENTS_CANCEL = STUDENT + "/enrollments/{enrollmentId}/cancel";
    public static final String STUDENT_ENROLLMENTS = STUDENT + "/enrollments";

    // === 학교 관리자 + 교사 API ===
    public static final String MANAGEMENT = API_V2 + "/management";
    public static final String MANAGEMENT_STUDENTS = MANAGEMENT + "/students";
    public static final String MANAGEMENT_COURSES = MANAGEMENT + "/courses";
    public static final String MANAGEMENT_COURSES_DETAIL = MANAGEMENT_COURSES + "/{courseId}";
    public static final String MANAGEMENT_COURSE_ENROLLED_STUDENTS = MANAGEMENT_COURSES + "/{courseId}/enrolled-students";

    // === 교사 + 학생 API ===
    public static final String COMMON = API_V2 + "/common";
    public static final String COMMON_COURSES = COMMON + "/courses";
    public static final String COMMON_COURSES_DETAIL = COMMON_COURSES + "/{courseId}";
    public static final String COMMON_NOTICES = COMMON + "/notices";
    public static final String COMMON_SCHEDULE = COMMON + "/schedule";

    public static final String AUTHENTICATED_USER = API_V2 + "/user";
}