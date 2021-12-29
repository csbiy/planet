package com.planet.dashboard.auth;

import org.springframework.stereotype.Component;

@Component
public class EmailAuth {

    public boolean validate(String email){

        return true;
    }
}
