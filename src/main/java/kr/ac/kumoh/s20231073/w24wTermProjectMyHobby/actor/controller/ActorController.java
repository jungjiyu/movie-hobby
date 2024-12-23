package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.actor.controller;


import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.actor.document.Actor;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.actor.service.ActorService;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.dto.response.CustomResponseBody;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/actor")
@RestController
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;

    // 배우 저장
    @PostMapping
    public ResponseEntity<CustomResponseBody<Actor>> createActor(@RequestBody Actor actor) {
        Actor savedActor = actorService.saveActor(actor);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(savedActor));
    }

    // 여러 배우 저장
    @PostMapping("/batch")
    public ResponseEntity<CustomResponseBody<List<Actor>>> createActors(@RequestBody List<Actor> actors) {
        List<Actor> savedActors = actorService.saveActors(actors);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(savedActors));
    }



    // 모든 배우 조회
    @GetMapping
    public ResponseEntity<CustomResponseBody<List<Actor>>> getAllActors() {
        List<Actor> actors = actorService.getAllActors();
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(actors));
    }

    // ID로 배우 조회
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponseBody<Actor>> getActorById(@PathVariable String id) {
        Actor actor = actorService.getActorById(id);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(actor));
    }

    // 배우 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponseBody<Actor>> updateActor(@PathVariable String id, @RequestBody Actor updatedActor) {
        Actor actor = actorService.updateActor(id, updatedActor);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(actor));
    }

    // 배우 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponseBody<Void>> deleteActor(@PathVariable String id) {
        actorService.deleteActor(id);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse());
    }

}
