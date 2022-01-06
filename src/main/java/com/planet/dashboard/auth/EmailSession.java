package com.planet.dashboard.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class EmailSession {

    private String auth;

    private String email;
}
