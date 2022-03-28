package com.planet.dashboard.controller;

import com.planet.dashboard.SessionManager;
import com.planet.dashboard.email.EmailService;
import com.planet.dashboard.email.EmailServiceImpl;
import com.planet.dashboard.email.EmailSession;
import com.planet.dashboard.controller.response.dto.ValidationResponse;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ValidateApiControllerTest {

    @Autowired
    ValidateApiController validateApiController;

    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;

    @ParameterizedTest
    @CsvSource({"katd6@naver.com"})
    @DisplayName("처음 가입하는 사람이 이메일 입력시 인증번호를 세션에 담습니다. ")
    void sendEmail_EmailAuthShouldIncludedInSession(String email) throws NoSuchFieldException, IllegalAccessException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        Header<ValidationResponse> response = validateApiController.sendEmail(email, request);
        EmailSession session = (EmailSession) SessionManager.getSession(request.getSession(), SessionManager.EMAIL_AUTH);
        Field auth_length = EmailServiceImpl.class.getDeclaredField("AUTH_LENGTH");
        auth_length.setAccessible(true);
        assertThat(session.getAuth()).isNotNull();
        assertThat(session.getAuth().length()).isEqualTo(auth_length.get(emailService));
        assertThat(session.getEmail()).isEqualTo(email);
        assertThat(response.getData().getStatus()).isEqualTo(ValidationResponse.Status.WAITING_AUTHORIZATION);
    }

    @ParameterizedTest
    @CsvSource({"first@naver.com,123456"})
    @DisplayName("이미 가입한 사람이 이메일 입력시 인증번호를 세션에 담지 않습니다. ")
    void sendEmail_ShouldNotSendAuth(String email,String password) {
        User user = User.builder()
                .email(email)
                .password(password)
                .build();
        userRepository.save(user);
        MockHttpServletRequest request = new MockHttpServletRequest();
        Header<ValidationResponse> response = validateApiController.sendEmail(email, request);
        assertThat(response.getData().getStatus()).isEqualTo(ValidationResponse.Status.DUPLICATE_MEMBER);
        assertThat(SessionManager.getSession(request.getSession(),SessionManager.EMAIL_AUTH)).isNull();
    }


}