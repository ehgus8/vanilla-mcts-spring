package com.example.vanilla_mcts_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/games/tictactoe")
    public String tictactoe() {
        return "games/tictactoe";
    }

    @GetMapping("/games/connect4")
    public String connect4() {
        return "games/connect4";
    }

    @GetMapping("/games/gomoku")
    public String gomoku() {
        return "games/gomoku";
    }
}
