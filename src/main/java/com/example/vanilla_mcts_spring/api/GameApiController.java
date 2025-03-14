package com.example.vanilla_mcts_spring.api;

import com.example.vanilla_mcts_spring.dto.GameStateDto;
import com.example.vanilla_mcts_spring.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameApiController {
    @Autowired
    GameService gameService;

    @PostMapping("/api/play/tictactoe")
    public ResponseEntity<GameStateDto> tictactoe(@RequestBody GameStateDto gameStateDto) {
        GameStateDto result = gameService.play(gameStateDto);
    }
}
