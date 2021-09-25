package main.models;

import main.types.Color;
import main.types.Coordinate;
import main.types.GameState;
import utils.models.Direction;

public class Board {

    static final int NUM_TOKENS_TO_WIN = 4;
    private Color[][] colors;

    public Board() {
        this.colors = new Color[Coordinate.ROWS][Coordinate.COLUMNS];
        for (int i = 0; i < Coordinate.ROWS; i++) {
            for (int j = 0; j < Coordinate.COLUMNS; j++) {
                this.colors[i][j] = Color.NULL;
            }
        }
    }

    public GameState putToken(int column, Color color) {
        Coordinate coordinate;
        main.types.Error error;
        int row = Coordinate.ROWS - 1;
        do {
            coordinate = new Coordinate(row, column);
            error = this.isValidCoordinate(coordinate);
            row--;
        } while (!error.isNull());
        this.colors[row + 1][column] = color;
        return this.isFinish(coordinate);
    }

    private main.types.Error isValidCoordinate(Coordinate coordinate) {
        assert !coordinate.isNull();

        main.types.Error error = main.types.Error.NULL;
        if (this.isOccupied(coordinate)) {
            error = main.types.Error.NOT_EMPTY;
        }
        return error;
    }

    public boolean isOccupied(Coordinate coordinate) {
        return this.getColor(coordinate) != Color.NULL;
    }

    public Color getColor(Coordinate coordinate) {
        assert !coordinate.isNull();

        return this.colors[coordinate.getRow()][coordinate.getColumn()];
    }

    public GameState isFinish(Coordinate coordinate) {
        if (isConnect4(coordinate)) {
            return GameState.CONNECT4;
        }
        if (isFull()) {
            return GameState.DRAW;
        }
        return GameState.NOT_FINISH;
    }

    private boolean isConnect4(Coordinate coordinate) {
        assert !coordinate.isNull();

        Direction[] directions = Direction.values();
        for (int i = 0; i <  Direction.values().length; i++){
            if(isConnect4(coordinate, directions[i])){
                return true;
            }
        }
        return false;
    }

    private boolean isConnect4(Coordinate coordinate, Direction direction) {
        assert !coordinate.isNull();

        int connectedTokens = countConnectedTokens(coordinate, direction);
        Coordinate displacedCoordinate = coordinate.displace(direction, NUM_TOKENS_TO_WIN - connectedTokens);
        if (displacedCoordinate.isValid() && getColor(displacedCoordinate) == getColor(coordinate)) {
            connectedTokens = countConnectedTokens(displacedCoordinate, direction);
        }
        return connectedTokens == NUM_TOKENS_TO_WIN;
    }

    private int countConnectedTokens(Coordinate coordinate, Direction direction) {
        assert !coordinate.isNull();

        int connectedTokens = 1;
        for (int i = 0; i < NUM_TOKENS_TO_WIN - 1; i++) {
            Coordinate nextCoordinate = getNextCoordinate(coordinate, direction);
            if (nextCoordinate.isValid() && this.getColor(coordinate) == this.getColor(nextCoordinate)) {
                connectedTokens++;
                coordinate = nextCoordinate;
            } else {
                return connectedTokens;
            }
        }
        return connectedTokens;
    }

    private Coordinate getNextCoordinate(Coordinate coordinate, Direction direction) {
        int nextRow = coordinate.getRow() + direction.getX();
        int nextColumn = coordinate.getColumn() + direction.getY();
        return new Coordinate(nextRow, nextColumn);
    }

    private boolean isFull() {
        for (int i = 0; i < Coordinate.COLUMNS; i++) {
            if (colors[0][i] == Color.NULL) {
                return false;
            }
        }
        return true;
    }


}
