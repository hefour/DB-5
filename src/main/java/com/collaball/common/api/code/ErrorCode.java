package com.collaball.common.api.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 공통
    INVALID_INPUT("COMMON_400", "잘못된 입력값입니다.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("COMMON_500", "서버 내부 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    // 인증
    INVALID_TOKEN("AUTH_401", "유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED),
    EXPIRED_TOKEN("AUTH_402", "만료된 토큰입니다.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("AUTH_403", "접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    LOGIN_FAILED("AUTH_404", "이메일 또는 비밀번호가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED),

    // 유저
    USER_NOT_FOUND("USER_404", "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    DUPLICATE_EMAIL("USER_409", "이미 사용 중인 이메일입니다.", HttpStatus.CONFLICT),

    // 프로젝트
    PROJECT_NOT_FOUND("PROJECT_404", "프로젝트를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    PROJECT_ACCESS_DENIED("PROJECT_403", "프로젝트에 대한 접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    ALREADY_PROJECT_MEMBER("PROJECT_409", "이미 프로젝트 멤버입니다.", HttpStatus.CONFLICT),

    // 태스크
    TASK_NOT_FOUND("TASK_404", "태스크를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // 평가
    EVALUATION_NOT_FOUND("EVAL_404", "평가를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    DUPLICATE_EVALUATION("EVAL_409", "이미 해당 프로젝트에서 평가를 완료했습니다.", HttpStatus.CONFLICT),
    SELF_EVALUATION_NOT_ALLOWED("EVAL_400", "자기 자신은 평가할 수 없습니다.", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
