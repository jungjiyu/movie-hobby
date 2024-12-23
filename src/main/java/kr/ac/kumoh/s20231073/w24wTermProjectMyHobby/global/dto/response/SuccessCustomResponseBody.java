package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.dto.response;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;

@Getter
@JsonTypeName("true")
public final class SuccessCustomResponseBody<T> extends CustomResponseBody<T> {
    private final T data;

    public SuccessCustomResponseBody() {
        data = null;
    }

    public SuccessCustomResponseBody(T result) {
        this.data = result;
    }
}
