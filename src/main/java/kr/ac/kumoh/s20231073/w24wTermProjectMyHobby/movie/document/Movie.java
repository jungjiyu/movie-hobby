package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.movie.document;

import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.actor.document.Actor;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.document.BaseDocument;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "movies")
public class Movie extends BaseDocument{

    @Id
    private String id; // MongoDB의 기본 식별자

    @Indexed(unique = true) // 유니크 제약 조건
    private String title; // 영화 제목

    private String director; // 감독

    private int year; // 제작 연도

    private String genre; // 장르

    private double rating; // 평점

    private String description; // 줄거리

    private String trailerUrl; // 예고편 URL

    private String thumbnailImageId; // 썸네일 이미지 ID

    @DBRef // 출연진 정보를 참조
    private List<Actor> cast;
}
