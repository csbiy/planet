package com.planet.dashboard.controller.response.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ValidationResponse implements Serializable {

    private String description;

    private Status status;

    public void setSuccess(){
        description = "인증에 성공하였습니다.";
        status = Status.SUCCESS;
    }
    public void setFail(){
        description = "인증에 실패하였습니다.";
        status = Status.FAIL;
    }

    public void setWaiting(){
        description = "인증 대기상태입니다.";
        status = Status.WAITING_AUTHORIZATION;
    }

    public void setDuplicate(){
        description = "이미 존재하는 회원입니다.";
        status = Status.DUPLICATE_MEMBER;
    }

    public enum Status{
        WAITING_AUTHORIZATION,
        SUCCESS,
        FAIL,
        DUPLICATE_MEMBER
    }
}
