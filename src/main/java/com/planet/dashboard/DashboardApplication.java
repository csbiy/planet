package com.planet.dashboard;

import com.planet.dashboard.entity.User;
import com.planet.dashboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;
	@PostConstruct
	void addAdminUser(){
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		User admin = User.builder()
				.email("test@naver.com")
				.password(passwordEncoder.encode("1234"))
				.build();
		userRepository.save(admin);

	}

}
