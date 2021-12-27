package com.planet.dashboard;

import com.planet.dashboard.entity.User;
import com.planet.dashboard.entity.UserId;
import com.planet.dashboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@SpringBootApplication
public class DashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;
	@PostConstruct
	void addAdminUser(){
		User admin = User.builder()
				.userId(new UserId("admin", "1234"))
				.createdAt(LocalDateTime.now())
				.build();
		userRepository.save(admin);
	}

}
