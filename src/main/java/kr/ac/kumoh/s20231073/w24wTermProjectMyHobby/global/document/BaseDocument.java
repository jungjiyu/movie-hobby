package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@SuperBuilder // 상속 클래스에서도 빌더 사용 가능
@NoArgsConstructor
public abstract class BaseDocument {

    @Field("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Field("modified_at")
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
