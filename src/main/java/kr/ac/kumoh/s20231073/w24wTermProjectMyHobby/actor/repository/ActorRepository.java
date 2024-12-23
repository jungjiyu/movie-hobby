package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.actor.repository;

import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.actor.document.Actor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActorRepository extends MongoRepository<Actor, String> {

}
