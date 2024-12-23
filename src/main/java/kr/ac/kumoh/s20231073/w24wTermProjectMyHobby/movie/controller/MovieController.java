package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.movie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.dto.response.CustomResponseBody;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.utils.ResponseUtil;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.movie.document.Movie;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.movie.dto.request.MovieRequestDto;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    /**
     * 영화 정보 저장
     * @param movieJson
     * @param imageFile
     * @return
     * @throws IOException
     */
    @PostMapping
    public ResponseEntity<CustomResponseBody<Movie>> createMovie(@RequestPart("movie") String movieJson,
                                                                 @RequestPart(value = "image", required = false) MultipartFile imageFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MovieRequestDto movieRequestDto = objectMapper.readValue(movieJson, MovieRequestDto.class);

        Movie savedMovie = movieService.saveMovie(movieRequestDto, imageFile);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(savedMovie));
    }




    /**
     * 여러 영화 정보 저장
     * @param movieDtos
     * @return
     */
    @PostMapping("/batch")
    public ResponseEntity<CustomResponseBody<List<Movie>>> createMoviesBatch(@RequestBody List<MovieRequestDto> movieDtos) {
        List<Movie> savedMovies = movieService.saveMoviesBatch(movieDtos);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(savedMovies));
    }



    /**
     * 모든 영화 조회
     * @return
     */
    @GetMapping
    public ResponseEntity<CustomResponseBody<List<Movie>>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(movies));
    }


    /**
     * id 기반 영화 정보 조회
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponseBody<Movie>> getMovieById(@PathVariable String id) {
        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(movie));
    }


    /**
     * id 기반 영화 정보 수정
     * @param id
     * @param updatedMovie
     * @param imageFile
     * @return
     * @throws IOException
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponseBody<Movie>> updateMovie(@PathVariable String id,
                                                                 @RequestPart("movie") Movie updatedMovie,
                                                                 @RequestPart(value = "image", required = false) MultipartFile imageFile) throws IOException {
        Movie movie = movieService.updateMovie(id, updatedMovie, imageFile);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(movie));
    }


    /**
     * id 기반 영화 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponseBody<Void>> deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse());
    }
}
