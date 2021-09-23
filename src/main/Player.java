package main;

import utils.Console;

public class Player {

    private Color color;
    private Board board;

    public Player(Color color, Board board) {
        assert !color.isNull();
        assert board != null;

        this.color = color;
        this.board = board;
    }

    public GameState putToken() {
        int column;
        Console console = Console.getInstance();
        Error error;
        do {
            column = console.readInt(this.color + Message.ENTER_COORDINATE_TO_PUT.toString()) - 1;
            error = this.isValidColumn(column);
            error.println();
        } while (!error.isNull());
        return this.board.putToken(column, this.color);
    }

    private Error isValidColumn(int column) {
        Coordinate coordinate = new Coordinate(0, column);
        if (!coordinate.isValid()) {
            return Error.WRONG_COLUMN;
        }
        if (this.board.isOccupied(coordinate)) {
            return Error.NOT_EMPTY;
        }
        return Error.NULL;
    }

    public void printWinner() {
        Message.PLAYER_WIN.println(this.color.name());
    }

}
