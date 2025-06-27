package com.example.back404.teamproject.common;

public class ResponseMessage {
    
    public static final String SUCCESS = "Success";
    
    // 조회 성공
    public static final String GET_SUBJECT_LIST_SUCCESS = "과목 전체 목록 조회 성공";
    public static final String GET_SUBJECT_DETAIL_SUCCESS = "과목 상세 정보 조회 성공";
    public static final String GET_TEACHER_LIST_SUCCESS = "교사 전체 목록 조회 성공";
    public static final String GET_STUDENT_LIST_SUCCESS = "학생 목록 조회 성공";
    public static final String GET_STUDENT_DETAIL_SUCCESS = "학생 상세 정보 조회 성공";
    public static final String GET_SCHOOL_LIST_SUCCESS = "학교 목록 조회 성공";
    public static final String GET_LECTURE_LIST_SUCCESS = "강의 목록 조회 성공";
    public static final String GET_LECTURE_DETAIL_SUCCESS = "강의 상세 정보 조회 성공";
        
    // 처리 성공
    public static final String UPDATE_SUBJECT_STATUS_SUCCESS = "과목 상태가 성공적으로 변경되었습니다.";
    public static final String APPROVE_SUBJECT_SUCCESS = "강의가 성공적으로 승인 및 생성되었습니다.";
    public static final String DELETE_SUBJECT_SUCCESS = "과목이 성공적으로 삭제되었습니다.";
    public static final String CREATE_SUBJECT_SUCCESS = "과목이 성공적으로 등록되었습니다.";
    public static final String UPDATE_SUBJECT_SUCCESS = "과목 정보가 성공적으로 수정되었습니다.";
    public static final String UPDATE_LECTURE_SUCCESS = "강의 정보가 성공적으로 수정되었습니다.";
    public static final String DELETE_LECTURE_SUCCESS = "강의가 성공적으로 삭제되었습니다.";
        
    // 수강신청 관련
    public static final String GET_REGISTERED_STUDENTS_SUCCESS = "수강신청 학생 명단 조회 성공";
    public static final String GET_ENROLLED_STUDENTS_SUCCESS = "수강 확정 학생 명단 조회 성공";
        
    // 예외 및 유효성 검사 메시지
    public static final String NOT_EXISTS_SUBJECT = "존재하지 않는 과목입니다.";
    public static final String NOT_EXISTS_LECTURE = "존재하지 않는 강의입니다.";
    public static final String NOT_EXISTS_TEACHER = "존재하지 않는 교사입니다.";
    public static final String NOT_EXISTS_STUDENT = "존재하지 않는 학생입니다";
    public static final String NOT_EXISTS_SCHOOL = "존재하지 않는 학교 ID입니다.";
    public static final String NO_AUTHORITY = "권한이 없습니다.";
    public static final String CANNOT_PROCESS_PENDING_ONLY = "'승인 대기' 상태의 과목만 처리할 수 있습니다.";
    public static final String CANNOT_DELETE_SUBJECT_HAS_LECTURE = "해당 과목으로 개설된 강의가 있어 삭제할 수 없습니다.";
    public static final String ALREADY_EXISTS_SUBJECT_ID = "이미 존재하는 과목 ID입니다.";

    // 공지사항 관련
    public static final String GET_NOTICE_LIST_SUCCESS = "공지사항 목록 조회 성공";
    public static final String GET_NOTICE_DETAIL_SUCCESS = "공지사항 상세 조회 성공";
    public static final String CREATE_NOTICE_SUCCESS = "공지사항 등록 성공";
    public static final String UPDATE_NOTICE_SUCCESS = "공지사항 수정 성공";
    public static final String DELETE_NOTICE_SUCCESS = "공지사항 삭제 성공";
    public static final String NOT_EXISTS_NOTICE = "존재하지 않는 공지사항입니다.";

    // 교사 관련
    public static final String TEACHER_SIGNUP_SUCCESS = "교사 회원가입 성공";
    public static final String TEACHER_LOGIN_SUCCESS = "교사 로그인 성공";
    public static final String GET_TEACHER_INFO_SUCCESS = "교사 정보 조회 성공";
    public static final String UPDATE_TEACHER_INFO_SUCCESS = "교사 정보 수정 성공";
    public static final String NOT_EXISTS_TEACHER_INFO = "교사 정보를 찾을 수 없습니다.";
    public static final String DUPLICATE_TEACHER_USERNAME = "이미 사용 중인 사용자명입니다.";
    public static final String DUPLICATE_TEACHER_EMAIL = "이미 등록된 이메일입니다.";

    // 계정 찾기 관련
    public static final String FIND_ID_SUCCESS = "아이디 찾기 성공";
    public static final String FIND_PASSWORD_SUCCESS = "비밀번호 재설정 성공";
    public static final String NOT_MATCH_ACCOUNT_INFO = "일치하는 계정 정보를 찾을 수 없습니다.";

    // 과목 관리 관련
    public static final String SUBJECT_CREATE_SUCCESS = "과목 등록 성공";
    public static final String SUBJECT_UPDATE_SUCCESS = "과목 수정 성공";
    public static final String SUBJECT_DELETE_SUCCESS = "과목 삭제 성공";
    public static final String DUPLICATE_SUBJECT_ID = "이미 존재하는 과목 ID입니다.";

    // 인증 관련
    public static final String INVALID_TOKEN = "유효하지 않은 토큰입니다.";
    public static final String EXPIRED_TOKEN = "만료된 토큰입니다.";
    public static final String UNAUTHORIZED_ACCESS = "접근 권한이 없습니다.";
    public static final String ACCOUNT_NOT_APPROVED = "승인되지 않은 계정입니다.";
}