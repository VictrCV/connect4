package main;

import main.models.Board;
import main.models.Player;
import main.types.Color;
import main.types.GameState;
import main.views.BoardView;
import main.views.Message;

public class Connect4 {

    private Board board;
    private BoardView boardView;
    private Player[] players;
    static final int NUM_PLAYERS = 2;
    private int activePlayer;

    private Connect4() {
        this.board = new Board();
        this.boardView = new BoardView();
        this.players = new Player[NUM_PLAYERS];
        for (int i = 0; i < NUM_PLAYERS; i++) {
            this.players[i] = new Player(Color.get(i), this.board);
        }
        this.activePlayer = 0;
    }

    private void play() {
        Message.TITLE.println();
        this.boardView.print(board);
        GameState gameState;
        do {
            gameState = this.players[this.activePlayer].putToken();
            if (gameState == GameState.NOT_FINISH) {
                this.activePlayer = this.getNextPlayer();
            }
            this.boardView.print(board);
        } while (gameState == GameState.NOT_FINISH);
        if (gameState == GameState.CONNECT4) {
            Message.PLAYER_WIN.println(this.players[this.activePlayer].getColor().name());
        } else {
            Message.DRAW.println();
        }
    }

    private int getNextPlayer() {
        return (this.activePlayer + 1) % NUM_PLAYERS;
    }

    public static void main(String[] arg) {
        new Connect4().play();
    }
}
