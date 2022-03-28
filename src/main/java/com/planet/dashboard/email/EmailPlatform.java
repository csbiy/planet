package com.planet.dashboard.email;


import lombok.Getter;

@Getter
public abstract class EmailPlatform {

    private final Integer port;

    private final String host;

    protected EmailPlatform( Integer port, String host) {
        this.port = port;
        this.host = host;
    }
}
