package com.planet.dashboard.controller;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

/***
 * @param : 응답할 dto 클래스 타입
 */
@Builder
@Getter
public class Header<T>  implements Serializable {
    T data;
    String description;
    String trxStartTime;

    public static <T> Header<T>  response(T data , String description ){
       return (Header<T>) Header.builder()
                .data(data)
                .description(description)
                .trxStartTime(LocalDateTime.now().toString())
                .build();
    }

    public static Header<Object> response(String description){
            return Header.builder()
                    .description(description)
                    .trxStartTime(LocalDateTime.now().toString())
                    .build();
    }

    public static Header<Object> error(String description){
        return Header.builder()
                .description(description)
                .trxStartTime(LocalDateTime.now().toString())
                .build();
    }



}
