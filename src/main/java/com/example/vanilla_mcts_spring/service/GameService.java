package com.example.vanilla_mcts_spring.service;

import com.example.vanilla_mcts_spring.ai.mcts.MCTS;
import com.example.vanilla_mcts_spring.ai.mcts.Node;
import com.example.vanilla_mcts_spring.dto.GameStateDto;
import com.example.vanilla_mcts_spring.games.BoardGame;

import java.awt.*;

public class GameService {
    public static void playOnceByMCTS(BoardGame game, int mctsIterations) {

        int currentPlayer = 1;
        int moveCount = 0;
        int winner = 0;

        game.displayBoard();
        Node root = new Node(null, null, currentPlayer, moveCount);
        MCTS.mcts(game, root, mctsIterations);
        Point action = root.getNextAction("maxVisit");
        currentPlayer = game.applyAction(currentPlayer, action);
        moveCount++;
        winner = game.checkWinner(currentPlayer * -1, action);

        for(Node child: root.children) {
            System.out.printf("Action:(%d, %d), Visit: %d UCB: %f\n",child.prevAction.y, child.prevAction.x, child.visit, Node.getUCB(child));
        }

        if(winner != 0) {

        }
        else if(moveCount == game.rows * game.cols) {

        }

    }
    public GameStateDto play(BoardGame game, GameStateDto gameStateDto) {
        game.board = gameStateDto.board;
        GameService.playOnceByMCTS(game, 100);
    }
}
