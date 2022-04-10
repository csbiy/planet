package com.planet.dashboard;

import com.planet.dashboard.entity.Board;
import com.planet.dashboard.entity.CrawlingComponent;
import com.planet.dashboard.entity.CrawlingSite;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.repository.CommentRepository;
import com.planet.dashboard.repository.CrawlingComponentRepository;
import com.planet.dashboard.repository.CrawlingSiteRepository;
import com.planet.dashboard.repository.UserRepository;
import com.planet.dashboard.repository.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
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
	CrawlingComponentRepository crawlingComponentRepository;
	@Autowired
	CrawlingSiteRepository crawlingSiteRepository;

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

	@PostConstruct
	@Profile("dev")
	@Transactional
	public void addCrawlingSite(){


		CrawlingSite site = CrawlingSite.builder()
				.name("인프런")
				.path("https://www.inflearn.com")
				.cssPath(".question-container a")
				.build();

		CrawlingComponent title = CrawlingComponent.builder()
				.name("title")
				.cssPath(".header__title h1")
				.site(site)
				.build();

		CrawlingComponent userName = CrawlingComponent.builder()
				.name("user")
				.cssPath(".header__sub-title .user-name")
				.site(site)
				.build();

		CrawlingComponent createdAt = CrawlingComponent.builder()
				.name("createdAt")
				.cssPath(".header__sub-title .sub-title__created-at")
				.site(site)
				.build();

		CrawlingComponent content = CrawlingComponent.builder()
				.name("content")
				.cssPath(".content__body.markdown-body")
				.site(site)
				.build();

		crawlingSiteRepository.save(site);
		crawlingComponentRepository.saveAll(List.of(title,userName,createdAt,content));

	}


}
