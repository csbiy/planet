package com.planet.dashboard.service;

import com.planet.dashboard.entity.AttachedFile;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.repository.FileRepository;
import com.planet.dashboard.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Commit;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FileHandlerTest {

    @Autowired
    FileHandler handler;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FileRepository fileRepository;

    String fileName;
    User savedUser;
    MockMultipartFile mockedFile;


    @BeforeEach
    void setUp() throws IOException {
        fileName = "picture";
        User user = User.builder()
                .email("test@email.com")
                .nickName("tester")
                .password("1234")
                .build();
        savedUser = userRepository.save(user);
        ClassPathResource resource = new ClassPathResource("img.png");
        mockedFile = new MockMultipartFile("picture.png", fileName, MediaType.IMAGE_PNG_VALUE, resource.getInputStream());
    }

    @Test
    @DisplayName("file을 입력받아, DB에 저장하고, 영속화된 entity를 반환합니다. ")
    void shouldSaveFileToDB() throws IOException {
        List<AttachedFile> savedFile = handler.saveFile( List.of(mockedFile), savedUser);
        Optional<AttachedFile> foundFile = fileRepository.findById(savedFile.get(0).getId());
        assertThat(foundFile.isPresent());
        assertThat(foundFile.get().getFileName()).isEqualTo(savedFile.get(0).getFileName());
    }


    @Test
    @DisplayName("사용자 id값 , 현재 날짜로 폴더를 만들어 사용자로부터 전달받은 파일을 특정경로에 저장합니다. ")
    void shouldTransferFile() throws IOException {
        String fileLocation = handler.createFileLocation(savedUser.getSeq(), fileName);
        handler.transferFile(mockedFile,fileLocation);
        File file = new File(fileLocation);
        assertThat(file.exists()).isTrue();
        file.delete();
    }
}