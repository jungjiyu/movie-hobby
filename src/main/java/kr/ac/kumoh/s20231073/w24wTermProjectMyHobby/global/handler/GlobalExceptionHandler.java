package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.handler;

import com.mongodb.DuplicateKeyException;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.enums.ExceptionType;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.utils.BusinessException;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.dto.response.CustomResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import static kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.utils.ResponseUtil.createFailureResponse;

@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CustomResponseBody<Void>> businessException(BusinessException e) {
        ExceptionType exceptionType = e.getExceptionType();
        return ResponseEntity.status(exceptionType.getStatus())
                .body(createFailureResponse(exceptionType));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<CustomResponseBody<Void>> handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("DuplicateKeyException 발생: {}", e.getMessage());
        return ResponseEntity
                .status(ExceptionType.DUPLICATED_TITLE.getStatus())
                .body(createFailureResponse(ExceptionType.DUPLICATED_TITLE));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomResponseBody<Void>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String customMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity
                .status(ExceptionType.BINDING_ERROR.getStatus())
                .body(createFailureResponse(ExceptionType.BINDING_ERROR, customMessage));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomResponseBody<Void>> dataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(createFailureResponse(ExceptionType.DUPLICATE_VALUE_ERROR));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    protected ResponseEntity<CustomResponseBody<Void>> handleMaxUploadSizeExceededException(
            MaxUploadSizeExceededException e) {
        log.info("handleMaxUploadSizeExceededException : {}", e.getMessage());

        return ResponseEntity
                .status(ExceptionType.FILE_TOO_LARGE.getStatus())
                .body(createFailureResponse(ExceptionType.FILE_TOO_LARGE));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponseBody<Void>> exception(Exception e) {
        log.error("Exception Message : {} ", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createFailureResponse(ExceptionType.UNEXPECTED_SERVER_ERROR));
    }
}


