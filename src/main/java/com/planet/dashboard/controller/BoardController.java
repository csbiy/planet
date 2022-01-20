package com.planet.dashboard.controller;

import com.planet.dashboard.controller.response.dto.BoardDto;
import com.planet.dashboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String getBoardList(@RequestParam Integer pageNum , Model model ){
        Page<BoardDto> boardsSortedByrCreatedAt = boardService.getBoardsSortedByrCreatedAt(pageNum);
        int totalPages = boardsSortedByrCreatedAt.getTotalPages();
        System.out.println("totalPages = " + totalPages);
        boardsSortedByrCreatedAt.getContent().stream().forEach(System.out::println);
        model.addAttribute("boards",boardService.getBoardsSortedByrCreatedAt(pageNum));
        return "board";
    }
}
