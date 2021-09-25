package main;

import main.controllers.BoardController;
import main.models.Player;
import main.types.Color;
import main.types.GameState;
import main.views.Message;

public class Connect4 {

    private BoardController boardController;
    private Player[] players;
    static final int NUM_PLAYERS = 2;
    private int activePlayer;

    private Connect4() {
        this.boardController = new BoardController();
        this.players = new Player[NUM_PLAYERS];
        for (int i = 0; i < NUM_PLAYERS; i++) {
            this.players[i] = new Player(Color.get(i), this.boardController.getBoard());
        }
        this.activePlayer = 0;
    }

    private void play() {
        Message.TITLE.println();
        this.boardController.print();
        GameState gameState;
        do {
            gameState = this.players[this.activePlayer].putToken();
            if (gameState == GameState.NOT_FINISH) {
                this.activePlayer = this.getNextPlayer();
            }
            this.boardController.print();
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
