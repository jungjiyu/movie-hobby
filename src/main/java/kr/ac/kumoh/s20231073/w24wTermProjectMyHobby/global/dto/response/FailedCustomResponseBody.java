package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.dto.response;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;

@Getter
@JsonTypeName("false")
public final class FailedCustomResponseBody extends CustomResponseBody<Void> {

    private final String msg;

    public FailedCustomResponseBody(String code, String msg) {
        this.setCode(code);
        this.msg = msg;
    }
}
