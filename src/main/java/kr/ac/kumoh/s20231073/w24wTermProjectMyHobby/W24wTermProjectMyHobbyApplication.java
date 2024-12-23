package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class W24wTermProjectMyHobbyApplication {

	public static void main(String[] args) {
		SpringApplication.run(W24wTermProjectMyHobbyApplication.class, args);
	}

}
