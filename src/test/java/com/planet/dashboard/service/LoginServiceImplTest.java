package com.planet.dashboard.service;

import com.planet.dashboard.dto.LoginForm;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
class LoginServiceImplTest {

    @Mock
    Model model;

    @Autowired UserRepository userRepository;
    @Autowired LoginService loginService;
    @Test
    @DisplayName("login시 session value가 정상적으로 추가되는지 확인합니다.")
    void isSessionCreatedWhenLogin(){

        //given
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        String email = "test@naver.com";
        String pw = "testpw";
        User user = User.builder()
                .email(email)
                .password(pw)
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(user);
        LoginForm loginForm = new LoginForm(email, pw);
        //when
        String redirectPath = loginService.login(mockHttpServletRequest, loginForm , model);

        //then
        Assertions.assertThat(redirectPath).isEqualTo("index");
        LoginForm value = (LoginForm) mockHttpServletRequest.getSession().getAttribute(SessionManager.SESSION_ID);
        Assertions.assertThat(value).isEqualTo(loginForm);
        Assertions.assertThat(model.getAttribute("loginFail")).isNull();


    }
}