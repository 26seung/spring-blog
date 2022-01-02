package com.one.blog.controller;

import com.one.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
}
