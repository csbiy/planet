package com.planet.dashboard.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginForm {

    @NotBlank
    private  String email;

    @NotBlank
    private  String password;


}
