package com.planet.dashboard.service;

import com.planet.dashboard.SessionManager;
import com.planet.dashboard.controller.request.dto.LoginForm;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class LoginServiceImplTest {

    @Mock
    Model model;

    @Autowired UserRepository userRepository;
    @Autowired LoginServiceImpl loginService;

    @Test
    @DisplayName("login시 session value가 정상적으로 추가되는지 확인합니다.")
    void isSessionCreatedWhenLogin(){

        //given
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        String email = "test@naver.com";
        String pw = "testpw";
        User user = createUser(email, pw);
        userRepository.save(user);
        LoginForm loginForm = new LoginForm(email, pw);
        //when
        String redirectPath = loginService.login(mockHttpServletRequest, loginForm , model);

        //then
        assertThat(redirectPath).isEqualTo("index");
        LoginForm value = (LoginForm) mockHttpServletRequest.getSession().getAttribute(SessionManager.LOGIN_ID.name());
        assertThat(value).isEqualTo(loginForm);
        assertThat(model.getAttribute("loginFail")).isNull();
    }

    @Test
    @DisplayName("logout시 session이 제거되는지 확인합니다.")
    public void logout(){
        // given
        String key = "sessionKey";
        String val = "sessionVal";
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        HttpSession createdSession = mockHttpServletRequest.getSession(true);
        createdSession.setAttribute(key,val);
        //when
        loginService.logout(mockHttpServletRequest);
        //then
        assertThatThrownBy(()->createdSession.getAttribute(key))
                .isInstanceOf(IllegalStateException.class);

    }

    @Test
    @DisplayName("loginForm의 정보가 member라면 true를 반환합니다.")
    void isMember(){
        String email = "test@test.com";
        String password = "testPw";
        User user = createUser(email, password);
        userRepository.save(user);

        LoginForm loginForm = new LoginForm();
        loginForm.setEmail(email);
        loginForm.setPassword(password);
        assertThat(loginService.isMember(loginForm)).isTrue();
    }

    @Test
    @DisplayName("loginForm의 정보가 member라면 false를 반환합니다.")
    void isNotMember(){
        String email = "test@test.com";
        String password = "testPw";
        User user = createUser(email, password);
        userRepository.save(user);

        LoginForm loginForm = new LoginForm();
        email = "diff@diff.com";
        loginForm.setEmail(email);
        loginForm.setPassword(password);
        assertThat(loginService.isMember(loginForm)).isFalse();
    }

    private User createUser(String email, String password) {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}