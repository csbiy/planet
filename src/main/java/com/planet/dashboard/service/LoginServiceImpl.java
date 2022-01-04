package com.planet.dashboard.service;

import com.planet.dashboard.SessionManager;
import com.planet.dashboard.controller.request.dto.LoginForm;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService{

    private final UserRepository userRepository;

    @Override
    public String login(HttpServletRequest request, LoginForm form, Model model) {

        if(isMember(form)){
            SessionManager.addSession(request.getSession(),SessionManager.SESSION_ID, form);
            return "index";
        }
        model.addAttribute("loginFail",true);
        return "login";
    }

    @Override
    public void logout(HttpServletRequest request) {
        request.getSession(false).invalidate();
    }



     boolean isMember(LoginForm loginForm) {
        Optional<User> foundUser = userRepository.findById(loginForm.getEmail());
        return foundUser.isPresent();
    }




}
