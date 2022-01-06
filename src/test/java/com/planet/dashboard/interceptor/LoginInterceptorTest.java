package com.planet.dashboard.interceptor;

import com.planet.dashboard.SessionManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class LoginInterceptorTest {

    LoginInterceptor loginInterceptor;
    MockHttpServletRequest request;
    MockHttpServletResponse response;

    @BeforeEach
    void init(){
        loginInterceptor =   new LoginInterceptor();
        request          =   new MockHttpServletRequest();
        response         =   new MockHttpServletResponse();
    }

    @Test
    @DisplayName("멤버가 아닌 client가 요청시 login page 로 redirect 합니다. ")
    void shouldRedirectIfSessionNotExist() throws Exception {
        String requestURI = "board";
        request.setRequestURI("/" + requestURI);
        boolean result = loginInterceptor.preHandle(request,response,null);
        int status = response.getStatus();
        assertThat(result).isFalse();
        assertThat(status).isEqualTo(HttpServletResponse.SC_MOVED_TEMPORARILY);
    }

    @Test
    @DisplayName("멤버인 client가 요청시 허용합니다.")
    void shouldAllowIfSessionExist() throws Exception {
        String requestURI = "some-path";
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionManager.LOGIN_ID.name(), "iamUser");
        request.setRequestURI("/" + requestURI);
        boolean result = loginInterceptor.preHandle(request,response,null);
        int status = response.getStatus();
        assertThat(result).isTrue();
        assertThat(status).isEqualTo(HttpServletResponse.SC_OK);
    }

}