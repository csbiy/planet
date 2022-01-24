package com.planet.dashboard.service;

import com.planet.dashboard.entity.AttachedFile;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.PropertyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FileHandler {

    private final FileRepository fileRepository;
    private final String fileLocation;

    public FileHandler(FileRepository fileRepository, PropertyResolver propertyResolver) {
        this.fileRepository = fileRepository;
        this.fileLocation = propertyResolver.getProperty("file.location");
    }

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.KOREA);

    private final String SUB_DIR = "/";

    public List<AttachedFile> saveFile(List<MultipartFile> files , User user){
        return files.stream().map((file)-> {
                String filename = file.getOriginalFilename();
                String fileLocation = createFileLocation(user.getSeq(), filename);
                transferFile(file, fileLocation);
                return saveFileToDB(filename, fileLocation);
        }).collect(Collectors.toList());
    }


    // save file to specific dir
    void transferFile(MultipartFile file, String fileLocation) {
        try {
            File targetFile = new File(fileLocation);
            targetFile.getParentFile().mkdirs();
            file.transferTo(targetFile);
        } catch (IOException e) {
            log.info("파일 저장 실패");
            e.printStackTrace();
        }
    }


    String createFileLocation(Long userNo , String fileName ){
        return fileLocation + SUB_DIR + timeFormatter.format(LocalDateTime.now()) + SUB_DIR + userNo + SUB_DIR + fileName ;
    }


    private AttachedFile saveFileToDB(String filename, String fileLocation) {
        return fileRepository.save( createFileEntity(filename, fileLocation));
    }


    private AttachedFile createFileEntity(String filename, String fileLocation) {
        AttachedFile attachedFile = AttachedFile.builder()
                .fileName(filename)
                .fileSavePath(fileLocation)
                .build();
        return attachedFile;
    }
}
