package com.planet.dashboard.auth;

import com.planet.dashboard.entity.User;
import com.planet.dashboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> foundUser = userRepository.findByEmail(username);
        if(foundUser.isEmpty()){
            return null;
        }
        return new PrincipalDetails(foundUser.get());
    }

}
