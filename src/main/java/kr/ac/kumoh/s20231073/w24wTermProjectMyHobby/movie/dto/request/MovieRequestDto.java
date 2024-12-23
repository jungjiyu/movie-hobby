package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.movie.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDto {
    private String title;
    private String director;
    private int year;
    private String genre;
    private double rating;
    private String description;
    private String trailerUrl;
    private String thumbnailImageId;
    private List<String> cast; // Actor ID 목록
}
