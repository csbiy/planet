package com.planet.dashboard;

import com.planet.dashboard.entity.Board;
import com.planet.dashboard.entity.Comment;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.repository.CommentRepository;
import com.planet.dashboard.repository.board.BoardRepository;
import com.planet.dashboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;

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
	@Profile("dev")
	void addInitData(){
		User admin = User.builder()
				.email("test@naver.com")
				.password("1234")
				.nickName("myNickName")
				.build();
		userRepository.save(admin);
		Comment comment = Comment.createComment("content", admin);
		Board board = Board.createBoard("admin", "h1", "h2");
		board.addComment(comment);
		commentRepository.save(comment);
		boardRepository.save(board);
	}

}
