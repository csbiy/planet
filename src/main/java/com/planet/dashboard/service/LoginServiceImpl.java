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
        Optional<User> foundUser = userRepository.findByEmail(form.getEmail());
        if(foundUser.isPresent()){
            SessionManager.addSession( request.getSession() , SessionManager.LOGIN_ID , foundUser.get());
            return "index";
        }
        model.addAttribute("loginFail",true);
        return "login";
    }

    @Override
    public void logout(HttpServletRequest request) {
        request.getSession(false).invalidate();
    }


    public boolean isMember(LoginForm loginForm) {
        Optional<User> foundUser = userRepository.findByEmail(loginForm.getEmail());
       return foundUser.isPresent() && foundUser.get().getPassword().equals(loginForm.getPassword());
    }
}
