package com.planet.dashboard.controller;

import com.planet.dashboard.SessionManager;
import com.planet.dashboard.controller.request.dto.BoardForm;
import com.planet.dashboard.controller.response.dto.AttachedFileDto;
import com.planet.dashboard.entity.AttachedFile;
import com.planet.dashboard.entity.Board;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.service.BoardService;
import com.planet.dashboard.service.FileHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final FileHandler fileHandler;

    @GetMapping
    public String getBoardList(@RequestParam Integer pageNum , Model model ){
//        model.addAttribute("boards",boardService.getBoardsSortedByrCreatedAt(pageNum));
        return "board";
    }

    @GetMapping("/{id}")
    public String getBoard(@PathVariable(name = "id") Long id , Model model){
        model.addAttribute("board",boardService.findById(id));
        return "boardDetail";
    }

    @PostMapping
    public String createBoard(BoardForm boardForm , HttpSession session){
        log.info("boardForm : {} , {} , file inside form : {} ", boardForm.getTitle(),boardForm.getContent(), boardForm.getFiles());
        User user = (User) SessionManager.getSession(session, SessionManager.LOGIN_ID);
        List<AttachedFile> attachedFiles = fileHandler.saveFile(boardForm.getFiles() , user);
        boardService.createBoardWithFile(boardForm, user.getNickName() , attachedFiles);
        return "board";
    }
}
