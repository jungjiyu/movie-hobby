package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.utils;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.enums.ExceptionType;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.dto.response.FailedCustomResponseBody;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.dto.response.CustomResponseBody;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.dto.response.SuccessCustomResponseBody;

public class ResponseUtil {

    public static CustomResponseBody<Void> createSuccessResponse() {
        return new SuccessCustomResponseBody<>();
    }

    public static <T> CustomResponseBody<T> createSuccessResponse(T data) {
        return new SuccessCustomResponseBody<>(data);
    }

    public static CustomResponseBody<Void> createFailureResponse(ExceptionType exceptionType) {
        return new FailedCustomResponseBody(
                exceptionType.getCode(),
                exceptionType.getMessage()
        );
    }

    public static CustomResponseBody<Void> createFailureResponse(ExceptionType exceptionType, String customMessage) {
        return new FailedCustomResponseBody(
                exceptionType.getCode(),
                customMessage
        );
    }
}
