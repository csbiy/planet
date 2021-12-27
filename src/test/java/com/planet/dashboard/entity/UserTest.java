package com.planet.dashboard.entity;

import com.planet.dashboard.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@Transactional
class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("@EmbeddedId 정상작동여부 확인")
    public void user(){
        //given
        UserId userId = new UserId("tester","1234");
        User user = new User.UserBuilder()
                .userId(userId)
                .createdAt(LocalDateTime.now())
                .build();
        User savedUser = userRepository.save(user);

        //when
        Optional<User> foundUser = userRepository.findById(userId);

        //then
        Assertions.assertThat(foundUser.isPresent());
        Assertions.assertThat(foundUser.get() ).isEqualTo(savedUser);
    }
}