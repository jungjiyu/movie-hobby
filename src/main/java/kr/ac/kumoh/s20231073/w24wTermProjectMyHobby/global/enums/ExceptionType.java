package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ExceptionType {

    // common
    UNEXPECTED_SERVER_ERROR(INTERNAL_SERVER_ERROR, "C001", "예상치못한 서버 에러 발생"),
    BINDING_ERROR(BAD_REQUEST, "C002", "바인딩시 에러 발생"),
    ESSENTIAL_FIELD_MISSING_ERROR(NO_CONTENT , "C003","필수적인 필드 부재"),
    INVALID_VALUE_ERROR(NOT_ACCEPTABLE , "C004","값이 유효하지 않음"),
    DUPLICATE_VALUE_ERROR(NOT_ACCEPTABLE , "C005","값이 중복됨"),


    // movie
    MOVIE_NOT_FOUND(NOT_FOUND, "M001", "존재하지 않는 영화"),
    DUPLICATED_TITLE(CONFLICT, "U002", "중복된 영화 제목"),

    // actor
    ACTOR_NOT_FOUND(NOT_FOUND, "A001", "존재하지 않는 배우"),
    DUPLICATED_ACTOR_NAME(CONFLICT, "A002", "중복된 배우 이름"),

    // file
    FILE_NOT_FOUND(NOT_FOUND, "F001", "파일이 존재하지 않음"),
    FILENAME_NOT_FOUND(NOT_FOUND, "F002", "파일의 이름이 존재하지 않음"),
    FAIL_FILE_UPLOAD(NOT_FOUND, "F003", "파일 업로드에 실패"),
    FILE_TOO_LARGE(PAYLOAD_TOO_LARGE, "F004", "파일 크기가 너무 큼"),
    DUPLICATED_FILENAME(NOT_FOUND, "F005", "중복된 파일명"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
