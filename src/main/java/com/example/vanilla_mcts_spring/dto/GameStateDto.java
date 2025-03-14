package com.example.vanilla_mcts_spring.dto;

import java.awt.*;

public class GameStateDto {
    public int[][] board;
    public int y;
    public int x;
    public int prevPlayer; // the player made this GameState(board, y, x, moveCount, result)
    public int moveCount;
    public int result; // 1: ended, 0: draw, -1: not ended

    public void setAction(Point action) {
        y = action.y;
        x = action.x;
    }
}
