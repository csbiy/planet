package com.planet.dashboard.controller;

import com.planet.dashboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String getBoardList(@RequestParam Integer pageNum , Model model ){
        model.addAttribute("boards",boardService.getBoardsSortedByrCreatedAt(pageNum));
        return "board";
    }
}
