package com.planet.dashboard.service;

import com.planet.dashboard.entity.AttachedFile;
import com.planet.dashboard.entity.Board;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Component
public class FileHandler {

    @Value("#{file.location}")
    String fileLocation;

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.KOREA);

    private final String SUB_DIR = "/";

    public List<AttachedFile> saveFile(List<MultipartFile> files){
        files.stream().forEach((file)-> {
            try {
                String originalFilename = file.getOriginalFilename();
                // save file to specific dir
                file.transferTo(new File(createFileLocation(fileLocation)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //TODO
    };

    private String createFileLocation(String fileLocation){
        return timeFormatter.format(LocalDateTime.now()) + SUB_DIR + fileLocation + SUB_DIR;
    }
}
