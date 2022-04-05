package com.planet.dashboard.controller;

import com.planet.dashboard.controller.request.dto.BoardForm;
import com.planet.dashboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;


    @GetMapping
    public String getBoardList(@RequestParam Integer pageNum , Model model ){
        model.addAttribute("boards",boardService.getBoardsSortedByrCreatedAt(pageNum));
        model.addAttribute("boardSize" , boardService.getNumOfBoards());
        return "board";
    }

    @GetMapping("/{id}")
    public String getBoard(@PathVariable(name = "id") Long id , Model model){
        model.addAttribute("board",boardService.findById(id));
        return "boardDetail";
    }

    @PostMapping
    public String createBoard(BoardForm boardForm , Authentication authentication){
        boardService.createBoard(boardForm, boardService.findNickName(authentication));
        return "redirect:/board?pageNum=0";
    }

    @GetMapping("/form")
    public String getBoardWriteForm(Model model){
        model.addAttribute("boardForm",new BoardForm());
        return "boardWrite";
    }
}
