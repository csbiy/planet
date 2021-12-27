package com.planet.dashboard.service;

import com.planet.dashboard.dto.LoginForm;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.entity.UserId;
import com.planet.dashboard.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LoginServiceImplTest {

    @Autowired UserRepository userRepository;
    @Autowired LoginService loginService;
    @Test
    @DisplayName("login시 session value가 정상적으로 추가되는지 확인합니다.")
    void isSessionCreatedWhenLogin(){

        //given
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        UserId userId = new UserId("tester", "1234");
        User user = User.builder()
                .userId(userId)
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(user);

        //when
        String redirectPath = loginService.login(mockHttpServletRequest, new LoginForm(userId.getUserId(), userId.getPassword()));

        //then
        Assertions.assertThat(redirectPath).isEqualTo("index");
        UserId value = (UserId) mockHttpServletRequest.getSession().getAttribute(SessionManager.SESSION_ID);
        Assertions.assertThat(value).isEqualTo(userId);


    }
}