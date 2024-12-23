package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.movie.service;

import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.enums.ExceptionType;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.utils.BusinessException;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.image.service.ImageService;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.movie.document.Movie;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.movie.dto.request.MovieRequestDto;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.actor.document.Actor;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.actor.service.ActorService;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;
    private final ImageService imageService;
    private final ActorService actorService;

    public Movie saveMovie(MovieRequestDto movieDto, MultipartFile imageFile) throws IOException {
        log.info("새로운 영화 '{}' 저장 시작", movieDto.getTitle());

        // Actor ID 목록을 Actor 객체로 변환
        List<Actor> actors = movieDto.getCast().stream()
                .map(actorService::getActorById) // Actor ID로 Actor 조회
                .collect(Collectors.toList());

        // 이미지 저장
        String imageId = null;
        if (imageFile != null && !imageFile.isEmpty()) {
            imageId = imageService.saveImage(imageFile).getId();
        }

        // Movie 객체 생성
        Movie movie = Movie.builder()
                .title(movieDto.getTitle())
                .director(movieDto.getDirector())
                .year(movieDto.getYear())
                .genre(movieDto.getGenre())
                .rating(movieDto.getRating())
                .description(movieDto.getDescription())
                .trailerUrl(movieDto.getTrailerUrl())
                .thumbnailImageId(imageId)
                .cast(actors) // 변환된 Actor 리스트 설정
                .build();

        return movieRepository.save(movie);
    }


    public List<Movie> saveMoviesBatch(List<MovieRequestDto> movieDtos) {
        log.info("영화 배치 저장 시작: 총 {}개의 영화", movieDtos.size());

        List<Movie> movies = movieDtos.stream()
                .map(dto -> {
                    List<Actor> actors = dto.getCast().stream()
                            .map(actorService::getActorById)
                            .collect(Collectors.toList());

                    return Movie.builder()
                            .title(dto.getTitle())
                            .director(dto.getDirector())
                            .year(dto.getYear())
                            .genre(dto.getGenre())
                            .rating(dto.getRating())
                            .description(dto.getDescription())
                            .trailerUrl(dto.getTrailerUrl())
                            .thumbnailImageId(dto.getThumbnailImageId())
                            .cast(actors)
                            .build();
                })
                .collect(Collectors.toList());

        List<Movie> savedMovies = movieRepository.saveAll(movies);
        log.info("영화 배치 저장 완료: 총 {}개의 영화 저장됨", savedMovies.size());

        return savedMovies;
    }



    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        log.info("총 {}개의 영화 조회 완료", movies.size());
        return movies;
    }

    public Movie getMovieById(String id) {
        log.info("ID '{}'의 영화 조회 시작", id);
        return movieRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("ID '{}'에 해당하는 영화가 존재하지 않음", id);
                    return new BusinessException(ExceptionType.MOVIE_NOT_FOUND);
                });
    }

    public Movie updateMovie(String id, Movie updatedMovie, MultipartFile imageFile) throws IOException {
        log.info("ID '{}'의 영화 수정 시작", id);

        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("ID '{}'에 해당하는 영화가 존재하지 않음", id);
                    return new BusinessException(ExceptionType.MOVIE_NOT_FOUND);
                });

        // 출연진 정보 업데이트
        List<Actor> updatedActors = saveActors(updatedMovie.getCast());
        updatedMovie.setCast(updatedActors);

        // 이미지 처리
        String newImageId = null;
        if (imageFile != null && !imageFile.isEmpty()) {
            if (existingMovie.getThumbnailImageId() != null) {
                imageService.deleteImage(existingMovie.getThumbnailImageId());
                log.info("기존 이미지 '{}' 삭제 완료", existingMovie.getThumbnailImageId());
            }
            newImageId = imageService.saveImage(imageFile).getId();
            log.info("새로운 이미지 '{}' 업로드 완료", newImageId);
        }

        // 기존 데이터를 기반으로 새로운 객체 생성
        Movie updatedEntity = existingMovie.builder()
                .title(updatedMovie.getTitle())
                .director(updatedMovie.getDirector())
                .year(updatedMovie.getYear())
                .genre(updatedMovie.getGenre())
                .rating(updatedMovie.getRating())
                .description(updatedMovie.getDescription())
                .trailerUrl(updatedMovie.getTrailerUrl())
                .thumbnailImageId(newImageId != null ? newImageId : existingMovie.getThumbnailImageId())
                .cast(updatedActors)
                .build();

        Movie savedMovie = movieRepository.save(updatedEntity);
        log.info("영화를 성공적으로 수정 완료, 영화 Title: {}", savedMovie.getTitle());

        return savedMovie;
    }

    public void deleteMovie(String id) {
        log.info("ID '{}'의 영화 삭제 시작", id);

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("ID '{}'에 해당하는 영화가 존재하지 않음, 삭제를 중단", id);
                    return new BusinessException(ExceptionType.MOVIE_NOT_FOUND);
                });

        if (movie.getThumbnailImageId() != null) {
            imageService.deleteImage(movie.getThumbnailImageId());
            log.info("이미지 '{}' 삭제 완료", movie.getThumbnailImageId());
        }

        movieRepository.deleteById(id);
        log.info("ID '{}'의 영화 삭제 완료", id);
    }

    private List<Actor> saveActors(List<Actor> actors) {
        if (actors == null || actors.isEmpty()) {
            return new ArrayList<>();
        }

        List<Actor> savedActors = new ArrayList<>();
        for (Actor actor : actors) {
            Actor savedActor = actorService.saveActor(actor);
            savedActors.add(savedActor);
            log.info("배우 '{}' 저장 완료, ID: {}", savedActor.getName(), savedActor.getId());
        }

        return savedActors;
    }
}
