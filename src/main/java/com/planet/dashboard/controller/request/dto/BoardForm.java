package com.planet.dashboard.controller.request.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class BoardForm {

    private String title;

    private String content;

    private List<MultipartFile> files;

}
