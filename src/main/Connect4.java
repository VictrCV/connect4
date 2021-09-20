package main;

public class Connect4 {

    private Board board;
    private Player[] players;
    static final int NUM_PLAYERS = 2;
    private int activePlayer;

    public Connect4(){
        this.board = new Board();
        this.players = new Player[NUM_PLAYERS];
        this.initPlayers();
    }

    private void initPlayers(){
        for(int i = 0; i < NUM_PLAYERS; i++){
            this.players[i] = new Player(Color.get(i), this.board);
        }
        this.activePlayer = 0;
    }

    private void play(){
        this.board.print();
        do{
            this.turn.play();
            this.board.print();
        }while(this.board.isConnect4());
        this.turn.printWinner();
    }

    public static void main(String arg[]){
        new Connect4().play();
    }
}
