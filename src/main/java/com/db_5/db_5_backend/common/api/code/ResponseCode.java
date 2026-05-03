package com.db_5.db_5_backend.common.api.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    // 공통
    OK("COMMON_200", "요청이 성공했습니다."),
    CREATED("COMMON_201", "생성이 완료되었습니다."),

    // 인증
    LOGIN_SUCCESS("AUTH_200", "로그인에 성공했습니다."),
    LOGOUT_SUCCESS("AUTH_201", "로그아웃에 성공했습니다."),
    TOKEN_REISSUED("AUTH_202", "토큰이 재발급되었습니다."),
    SIGNUP_SUCCESS("AUTH_203", "회원가입이 완료되었습니다."),

    // 유저
    USER_FOUND("USER_200", "사용자 조회에 성공했습니다."),

    // 프로젝트
    PROJECT_CREATED("PROJECT_201", "프로젝트가 생성되었습니다."),
    PROJECT_FOUND("PROJECT_200", "프로젝트 조회에 성공했습니다."),
    PROJECT_UPDATED("PROJECT_202", "프로젝트가 수정되었습니다."),
    PROJECT_DELETED("PROJECT_203", "프로젝트가 삭제되었습니다."),

    // 태스크
    TASK_CREATED("TASK_201", "태스크가 생성되었습니다."),
    TASK_FOUND("TASK_200", "태스크 조회에 성공했습니다."),
    TASK_UPDATED("TASK_202", "태스크가 수정되었습니다."),
    TASK_DELETED("TASK_203", "태스크가 삭제되었습니다."),

    // 평가
    EVALUATION_CREATED("EVAL_201", "평가가 완료되었습니다."),
    EVALUATION_FOUND("EVAL_200", "평가 조회에 성공했습니다."),
    EVALUATION_UPDATED("EVAL_202", "평가가 수정되었습니다.");

    private final String code;
    private final String message;
}
