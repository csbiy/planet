package com.planet.dashboard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class RegisterForm {

    @NotBlank
    @Size(min = 5, max = 200)
    private String email;

    private boolean validated;

    @NotBlank
    @Size(min = 4,max = 20)
    private String firstPw;

    @NotBlank
    @Size(min = 4,max = 20)
    private String SecondPw;

}
