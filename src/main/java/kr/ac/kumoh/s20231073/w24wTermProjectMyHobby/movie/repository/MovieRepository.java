package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.movie.repository;

import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.movie.document.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {
}