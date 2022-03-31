package com.planet.dashboard;

import com.planet.dashboard.entity.Board;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.repository.CommentRepository;
import com.planet.dashboard.repository.UserRepository;
import com.planet.dashboard.repository.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.UUID;


@SpringBootApplication
public class DashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;
	@Autowired
	BoardRepository boardRepository;
	@Autowired
	CommentRepository commentRepository;

	@Autowired
	PasswordEncoder passwordEncoder;


	@PostConstruct
	@Profile("dev")
	public void addTestUser(){
		userRepository.save(User.createAdminUser(passwordEncoder.encode("1234"),"test@naver.com"));
	}

	@PostConstruct
	@Profile("dev")
	public void addTestBoard(){
		for(int i = 0; i< 63 ; i++){
			boardRepository.save(Board.createBoard("kcs","hello", UUID.randomUUID().toString().substring(0,20)));
		}
	}


}
