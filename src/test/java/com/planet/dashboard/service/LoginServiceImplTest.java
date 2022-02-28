package com.planet.dashboard.service;

import com.planet.dashboard.entity.Role;
import com.planet.dashboard.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class LoginServiceImplTest {

    @Autowired UserRepository userRepository;
    @Autowired LoginServiceImpl loginService;

    @Test
    @WithMockUser(username = "test@naver.com",password = "testPw",roles = "USER")
    @DisplayName("login시 session value가 정상적으로 추가되는지 확인합니다.")
    void isSessionCreatedWhenLogin(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        assertThat(user.getUsername()).isEqualTo("test@naver.com");
        assertThat(user.getPassword()).isEqualTo("testPw");
        assertThat(user.getAuthorities().toString()).contains(Role.ROLE_USER.toString());

    }

    @Test
    @WithMockUser(username = "test@naver.com",password = "testPw",roles = "USER")
    @DisplayName("logout시 session이 제거되는지 확인합니다.")
    public void logout(){
        SecurityContextHolder.clearContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertThat(authentication).isNull();
    }

}