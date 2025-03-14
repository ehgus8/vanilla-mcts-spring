package com.example.vanilla_mcts_spring.service;

import com.example.vanilla_mcts_spring.ai.mcts.MCTS;
import com.example.vanilla_mcts_spring.ai.mcts.Node;
import com.example.vanilla_mcts_spring.dto.GameStateDto;
import com.example.vanilla_mcts_spring.games.BoardGame;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class GameService {
    private static void playOnceByMCTS(BoardGame game, int mctsIterations, GameStateDto gameStateDto) {

        int currentPlayer = -gameStateDto.prevPlayer;
        int winner = 0;

        game.displayBoard();
        Node root = new Node(null, null, currentPlayer, gameStateDto.moveCount);
        MCTS.mcts(game, root, mctsIterations);
        Point action = root.getNextAction("maxVisit");
        gameStateDto.setAction(action);
        currentPlayer = game.applyAction(currentPlayer, action);
        gameStateDto.moveCount++;
        gameStateDto.prevPlayer = -currentPlayer;
        winner = game.checkWinner(currentPlayer * -1, action);

        for(Node child: root.children) {
            System.out.printf("Action:(%d, %d), Visit: %d UCB: %f\n",child.prevAction.y, child.prevAction.x, child.visit, Node.getUCB(child));
        }

        if(winner != 0) {
            gameStateDto.result = 1;
        }
        else if(gameStateDto.moveCount == game.rows * game.cols) {
            gameStateDto.result = 0;
        }
    }
    public GameStateDto play(BoardGame game, GameStateDto gameStateDto) {
        game.board = gameStateDto.board; // shallow copy
        GameService.playOnceByMCTS(game, 100, gameStateDto);
        return gameStateDto;
    }
}
