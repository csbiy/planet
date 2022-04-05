package com.planet.dashboard.controller.request.dto;

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
    @Size(min = 1,max = 10)
    private String nickName;

    @NotBlank
    @Size(min = 6,max = 20)
    private String firstPw;

    @NotBlank
    @Size(min = 6,max = 20)
    private String SecondPw;

}
