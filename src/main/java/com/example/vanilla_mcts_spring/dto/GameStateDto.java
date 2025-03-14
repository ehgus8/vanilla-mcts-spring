package com.example.vanilla_mcts_spring.dto;

public class GameStateDto {
    public int[][] board;
    int y;
    int x;
    int result; // 1: 승리, 0: 무승부, -1: 패배, -2: 안 끝남.
}
