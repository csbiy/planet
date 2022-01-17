package com.planet.dashboard;

import com.planet.dashboard.auth.EmailSender;
import com.planet.dashboard.entity.Board;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.repository.BoardRepository;
import com.planet.dashboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@SpringBootApplication
public class DashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;
	@Autowired
	BoardRepository boardRepository;
	@PostConstruct
	@Profile("dev")
	void addInitData(){
		User admin = User.builder()
				.email("test@naver.com")
				.password("1234")
				.build();
		userRepository.save(admin);
	}

}
