package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.actor.service;

import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.actor.document.Actor;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.actor.repository.ActorRepository;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.enums.ExceptionType;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.utils.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActorService {

    private final ActorRepository actorRepository;

    // 배우 저장
    public Actor saveActor(Actor actor) {
        log.info("새로운 배우 '{}' 저장 시작", actor.getName());
        return actorRepository.save(actor);
    }

    // 배우 여러 개 저장
    public List<Actor> saveActors(List<Actor> actors) {
        log.info("배우 {}명 저장 시작", actors.size());
        List<Actor> savedActors = actorRepository.saveAll(actors);
        log.info("배우 {}명 저장 완료", savedActors.size());
        return savedActors;
    }


    // 모든 배우 조회
    public List<Actor> getAllActors() {
        log.info("모든 배우 정보 조회 시작");
        List<Actor> actors = actorRepository.findAll();
        log.info("총 {}명의 배우 조회 완료", actors.size());
        return actors;
    }

    // ID로 배우 조회
    public Actor getActorById(String id) {
        log.info("ID '{}'로 배우 조회 시작", id);
        return actorRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("ID '{}'에 해당하는 배우를 찾을 수 없음", id);
                    return new BusinessException(ExceptionType.ACTOR_NOT_FOUND);
                });
    }

    // 배우 정보 수정
    public Actor updateActor(String id, Actor updatedActor) {
        log.info("ID '{}'의 배우 정보 수정 시작", id);

        Actor existingActor = actorRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("ID '{}'에 해당하는 배우를 찾을 수 없음", id);
                    return new BusinessException(ExceptionType.ACTOR_NOT_FOUND);
                });        // 필드 업데이트


        Actor updatedActorBuilt = existingActor.builder()
                .name(updatedActor.getName())
                .role(updatedActor.getRole())
                .description(updatedActor.getDescription())
                .profileImageId(updatedActor.getProfileImageId())
                .build();

        Actor savedActor = actorRepository.save(updatedActorBuilt);
        log.info("ID '{}'의 배우 정보 수정 완료", savedActor.getId());
        return savedActor;
    }

    // 배우 삭제
    public void deleteActor(String id) {
        log.info("ID '{}'의 배우 삭제 시작", id);

        Actor existingActor = actorRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("ID '{}'에 해당하는 배우를 찾을 수 없음", id);
                    return new BusinessException(ExceptionType.ACTOR_NOT_FOUND);
                });

        actorRepository.delete(existingActor);
        log.info("ID '{}'의 배우 삭제 완료", id);
    }
}
