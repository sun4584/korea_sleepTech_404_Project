package com.example.back404.teamproject.common;

public class ApiMappingPattern {
    
    // ========== 기본 패턴 ========== //
    public static final String API_V1 = "/api/v1";
    
    // ========== 공통 기능 ========== //
    public static final String API_COMMON = "/api/v1/common";
    
    // ========== 인증 관련 ========== //
    public static final String API_AUTH_ADMIN = "/api/v1/auth/admin";
    public static final String API_AUTH_TEACHER = "/api/v1/auth/teacher";
    public static final String API_AUTH_STUDENT = "/api/v1/auth/student";
    
    // ========== 역할별 전용 ========== //
    public static final String API_ADMIN = "/api/v1/admin";
    public static final String API_TEACHER = "/api/v1/teacher";
    public static final String API_STUDENT = "/api/v1/student";
    
    // ========== 관리 기능 ========== //
    public static final String API_MANAGE = "/api/v1/manage";
    public static final String API_MANAGE_TEACHER = API_MANAGE + "/teachers";
    public static final String API_MANAGE_STUDENT = API_MANAGE + "/students";
    public static final String API_MANAGE_SUBJECT = API_MANAGE + "/subjects";
    public static final String API_MANAGE_LECTURE = API_MANAGE + "/lectures";
    
    // ========== 교사 + 학생 공통 ========== //
    public static final String API_CLASSROOM = "/api/v1/classroom";
    
    // ========== 리소스별 ========== //
    public static final String SUBJECTS_BASE = "/api/v1/subjects";
    public static final String LECTURES_BASE = "/api/v1/lectures";
    public static final String NOTICES_BASE = "/api/v1/notices";
    public static final String SCHOOLS_BASE = "/api/v1/schools";
    
    // ========== 학생 전용 기능 ========== //
    public static final String STUDENT_REGISTRATIONS = "/api/v1/course-registrations";
    public static final String STUDENT_SCHEDULES = "/api/v1/schedules";
    
    // ========== 기타 ========== //
    public static final String EMAIL_VERIFICATIONS = "/api/v1/email-verifications";
    public static final String SCHOOL_APPLICATIONS = "/api/v1/school-applications";
    
    // ========== 하위 호환을 위한 별칭들 ========== //
    public static final String SUBJECT_API = SUBJECTS_BASE;
    public static final String LECTURE_API = LECTURES_BASE;
    public static final String NOTICE_API = NOTICES_BASE;
    public static final String MANAGE_SUBJECT = API_MANAGE_SUBJECT;
}