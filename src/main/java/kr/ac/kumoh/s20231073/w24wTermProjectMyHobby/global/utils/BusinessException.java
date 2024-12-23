package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.utils;

import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.enums.ExceptionType;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private final ExceptionType exceptionType;

    public BusinessException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
