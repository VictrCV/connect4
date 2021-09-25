package main.controllers;

import main.views.BoardView;
import main.models.Board;

public class BoardController {

    Board board;
    BoardView boardView;

    public BoardController() {
        this.board = new Board();
        this.boardView = new BoardView();
    }

    public void print() {
        boardView.print(board);
    }

    public Board getBoard() {
        return board;
    }
}
