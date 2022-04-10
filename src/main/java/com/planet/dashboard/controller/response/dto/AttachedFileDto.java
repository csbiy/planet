package com.planet.dashboard.controller.response.dto;

import com.planet.dashboard.entity.AttachedFile;
import lombok.Getter;

@Getter
public class AttachedFileDto {

    private final Long id;

    private final String fileName;

    public static AttachedFileDto createAttachedFileDto(AttachedFile file){
        return new AttachedFileDto(file);
    }

    private AttachedFileDto(AttachedFile file){
        this.id = file.getId();
        this.fileName = file.getFileName();
    }


}
