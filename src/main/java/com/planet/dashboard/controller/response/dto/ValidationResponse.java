package com.planet.dashboard.controller.response.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ValidationResponse implements Serializable {

    private String description;

    private Status status;

    public void setSuccess(String description){
        this.status = Status.SUCCESS;
        this.description = description;
    }
    public void setFail(String description){
        this.status = Status.FAIL;
        this.description = description;
    }

    public void setWaiting(String description){
        this.status = Status.WAITING_AUTHORIZATION;
        this.description = description;
    }

    public void setDuplicate(String description){
        this.status = Status.DUPLICATE_MEMBER;
        this.description = description;
    }

    public enum Status{
        WAITING_AUTHORIZATION,
        SUCCESS,
        FAIL,
        DUPLICATE_MEMBER
    }
}
