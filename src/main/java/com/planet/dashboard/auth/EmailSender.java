package com.planet.dashboard.auth;

import lombok.Getter;
import org.springframework.core.env.PropertyResolver;
import org.springframework.stereotype.Component;

@Component
@Getter
public final class EmailSender {

    private final EmailPlatform emailPlatform;

    private final String id;

    private final String password;

    public EmailSender(PropertyResolver  resolver) {

        String platform = resolver.getProperty("smtp.platform");
        if(platform.equals("naver")){
            this.emailPlatform = new Naver();
        }else if(platform.equals("gmail")){
            this.emailPlatform = new Gmail();
        }else{
            this.emailPlatform = null;
        }
        this.id = resolver.getProperty("smtp.sender.id");
        this.password = resolver.getProperty("smtp.sender.password");

    }

}
