package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.actor.document;

import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.document.BaseDocument;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "actors")
public class Actor extends BaseDocument {

    @Id
    private String id; // MongoDB의 기본 식별자
    private String name; // 배우 이름
    private String role; // 배역
    private String description; // 배우 설명
    private String profileImageId; // 배우 프로필 이미지 ID
}