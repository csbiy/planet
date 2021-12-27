package com.planet.dashboard.service;

import com.planet.dashboard.dto.LoginForm;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.entity.UserId;
import com.planet.dashboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService{

    private final UserRepository userRepository;

    @Override
    public String login(HttpServletRequest request, LoginForm form) {

        UserId userId = new UserId(form.getId(), form.getPassword());
        log.info("userId : {} ", userId);
        if(validateForm(userId)){
            addSession(request,userId);
            return "index";
        }
        return "login";
    }

    private boolean validateForm(UserId userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        return foundUser.isPresent();
    }

    @Override
    public void addSession(HttpServletRequest request , UserId userId) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionManager.SESSION_ID , userId );
    }


}
