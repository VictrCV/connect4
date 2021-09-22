package main;

public class Connect4 {

    private Board board;
    private Player[] players;
    static final int NUM_PLAYERS = 2;
    private int activePlayer;

    private Connect4(){
        this.board = new Board();
        this.players = new Player[NUM_PLAYERS];
        for(int i = 0; i < NUM_PLAYERS; i++){
            this.players[i] = new Player(Color.get(i), this.board);
        }
        this.activePlayer = 0;
    }

    private void play(){
        Message.TITLE.println();
        this.board.print();
        GameState gameState;
        do{
            gameState = this.players[this.activePlayer].putToken();
            if (gameState == GameState.NOT_FINISH){
                this.activePlayer = this.getNextPlayer();
            }
            this.board.print();
        }while(gameState == GameState.NOT_FINISH);
        if(gameState == GameState.CONNECT4) {
            this.players[this.activePlayer].printWinner();
        }else{
            Message.DRAW.println();
        }
    }

    private Color getActiveColor() {
        return this.players[this.activePlayer].getColor();
    }

    private int getNextPlayer(){
        return (this.activePlayer+1) % NUM_PLAYERS;
    }

    public static void main(String arg[]){
        new Connect4().play();
    }
}
