package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "success")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SuccessCustomResponseBody.class, name = "true"),
        @JsonSubTypes.Type(value = FailedCustomResponseBody.class, name = "false")
})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public sealed abstract class CustomResponseBody<T> permits SuccessCustomResponseBody, FailedCustomResponseBody {
    private String code;
}
