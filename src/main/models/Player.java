package main.models;

import main.types.Color;
import main.types.Coordinate;
import main.types.Error;
import main.types.GameState;
import main.views.ErrorView;
import main.views.Message;
import utils.views.Console;

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

        Console console = Console.getInstance();
        Error error;
        int column;
        do {
            column = console.readInt(this.color + Message.ENTER_COORDINATE_TO_PUT.toString()) - 1;
            error = this.isValidColumn(column);
            new ErrorView().println(error);
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

    public Color getColor() {
        return color;
    }

}
