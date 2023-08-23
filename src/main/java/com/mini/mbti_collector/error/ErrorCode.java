package com.mini.mbti_collector.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    JWT_EXPIRED(1, HttpStatus.BAD_REQUEST, "토큰이 만료됐습니다."),
    JWT_WRONG(2, HttpStatus.BAD_REQUEST, "토큰값이 올바르지 않습니다."),
    NO_ADMIN(3, HttpStatus.BAD_REQUEST, "다른 회원을 삭제할 권한이 없습니다."),
    USER_ID_NULL(4, HttpStatus.BAD_REQUEST, "회원 번호는 null일 수 없습니다."),
    USER_NOT_FOUND(5, HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다."),
    FIELD_VALIDATION_FAILED(6, HttpStatus.BAD_REQUEST, "입력한 값이 올바르지 않습니다."),
    USER_ACCOUNT_NULL(7, HttpStatus.BAD_REQUEST, "회원 정보는 null일 수 없습니다."),
    USER_ACCOUNT_QUERY_FAILED(8, HttpStatus.INTERNAL_SERVER_ERROR, "회원 조회에 실패했습니다."),
    USER_ACCOUNT_UPDATE_FAILED(9, HttpStatus.INTERNAL_SERVER_ERROR, "회원 수정에 실패했습니다."),
    USER_ACCOUNT_DELETE_FAILED(10, HttpStatus.INTERNAL_SERVER_ERROR, "회원 삭제에 실패했습니다."),
    DUPLICATED_USER_NAME(11, HttpStatus.BAD_REQUEST, "가입하시려는 아이디가 이미 존재합니다 :( 다른 아이디로 가입해주세요!"),
    AUTHENTICATION_WRONG(12, HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."),
    AUTHORIZATION_WRONG(13, HttpStatus.FORBIDDEN, "권한이 없는 사용자입니다."),
    BAD_CREDENTIALS(21, HttpStatus.BAD_REQUEST, "Access Token의 잘못된 계정정보입니다."),
    USERNAME_OR_PASSWORD_WRONG(22, HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호가 올바르지 않습니다."),
    SERVER_ERROR(99,HttpStatus.INTERNAL_SERVER_ERROR ,"서버 오류로 정상적으로 요청을 처리할 수 없습니다.");

    private int code;
    private HttpStatus status;  // reponse status 값
    private String message;     // 오류에 관련한 메세지
}

