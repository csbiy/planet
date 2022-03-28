package com.planet.dashboard.service;

import com.planet.dashboard.SessionManager;
import com.planet.dashboard.email.EmailSession;
import com.planet.dashboard.controller.request.dto.RegisterForm;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;

import java.util.Optional;

@SpringBootTest
class RegisterServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RegisterService registerService;
    @ParameterizedTest
    @CsvSource({"test@email.com,123456"})
    @DisplayName("회원가입시 DB에 회원정보가 정상적으로 생성되는지 확인합니다.")
    void register(String email , String password) {
        // given
        RegisterForm registerForm = new RegisterForm();
        registerForm.setFirstPw(password);
        registerForm.setSecondPw(password);
        MockHttpSession mockHttpSession = new MockHttpSession();
        // 인증된 회원이라 가정하고 "auth" string을 넣어주었습니다.
        SessionManager.addSession(mockHttpSession,SessionManager.EMAIL_AUTH,new EmailSession("auth",email));
        // when
        User registeredUser = registerService.register(registerForm, mockHttpSession);
        //then
<<<<<<< HEAD
        Optional<User> foundUser = userRepository.findById(registeredUser.getSeq());
=======
        Optional<User> foundUser = userRepository.findByEmail(email);
>>>>>>> feat-01
        Assertions.assertThat(foundUser.isPresent()).isTrue();
        Assertions.assertThat(foundUser.get().getPassword()).isEqualTo(password);
    }
}