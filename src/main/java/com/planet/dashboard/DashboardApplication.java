package com.planet.dashboard;

<<<<<<< HEAD
import com.planet.dashboard.entity.Board;
import com.planet.dashboard.entity.Comment;
=======
>>>>>>> feat-01
import com.planet.dashboard.entity.User;
import com.planet.dashboard.repository.CommentRepository;
import com.planet.dashboard.repository.board.BoardRepository;
import com.planet.dashboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;
=======
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
>>>>>>> feat-01

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
	@PostConstruct
<<<<<<< HEAD
	@Profile("dev")
	void addInitData(){
		User admin = User.builder()
				.email("test@naver.com")
				.password("1234")
				.nickName("myNickName")
=======
	void addAdminUser(){
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		User admin = User.builder()
				.email("test@naver.com")
				.password(passwordEncoder.encode("1234"))
>>>>>>> feat-01
				.build();
		userRepository.save(admin);
		Comment comment = Comment.createComment("content", admin);
		Board board = Board.createBoard("admin", "h1", "h2");
		board.addComment(comment);
		commentRepository.save(comment);
		boardRepository.save(board);
	}

}
