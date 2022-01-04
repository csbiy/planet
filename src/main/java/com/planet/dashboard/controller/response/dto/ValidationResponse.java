package com.planet.dashboard.controller.response.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ValidationResponse implements Serializable {

    private String description;

    public void setSuccess(){
        description = "인증에 성공하였습니다.";
    }
    public void setFail(){
        description = "인증에 실패하였습니다.";
    }
}
