package com.planet.dashboard.controller.request.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class EmailForm {

    @NotBlank
    @Size(min = 5, max = 200)
    @Email
    private String email;



}
