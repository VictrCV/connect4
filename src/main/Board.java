package main;

import utils.Console;
import utils.Direction;

class Board {

    static final int NUM_TOKENS_TO_WIN = 4;
    private Color[][] colors;

    Board() {
        this.colors = new Color[Coordinate.ROWS][Coordinate.COLUMNS];
        for (int i = 0; i < Coordinate.ROWS; i++) {
            for (int j = 0; j < Coordinate.COLUMNS; j++) {
                this.colors[i][j] = Color.NULL;
            }
        }
    }

    GameState putToken(int column, Color color) {
        Coordinate coordinate;
        Error error;
        int row = Coordinate.ROWS - 1;
        do {
            coordinate = new Coordinate(row, column);
            error = this.isValidCoordinate(coordinate);
            row--;
        } while (!error.isNull());
        this.colors[row + 1][column] = color;
        return this.isFinish(coordinate);
    }

    private Error isValidCoordinate(Coordinate coordinate) {
        assert !coordinate.isNull();

        Error error = Error.NULL;
        if (this.isOccupied(coordinate)) {
            error = Error.NOT_EMPTY;
        }
        return error;
    }

    boolean isOccupied(Coordinate coordinate) {
        return this.getColor(coordinate) != Color.NULL;
    }

    private Color getColor(Coordinate coordinate) {
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

    boolean isConnect4(Coordinate coordinate) {
        assert !coordinate.isNull();

        Direction[] directions = Direction.values();
        int i = 0;
        while (i < Direction.values().length && !isConnect4(coordinate, directions[i])) {
            i++;
        }
        return isConnect4(coordinate, directions[i]);
    }

    private boolean isConnect4(Coordinate coordinate, Direction direction) {
        assert !coordinate.isNull();

        int connectedTokens = countConnectedTokens(coordinate, direction);
        int displacedRow = coordinate.getRow() - direction.getX() * (NUM_TOKENS_TO_WIN - connectedTokens); //a coordenada
        int displacedColumn = coordinate.getColumn() - direction.getY() * (NUM_TOKENS_TO_WIN - connectedTokens);
        Coordinate displacedCoordinate = new Coordinate(displacedRow, displacedColumn);
        if (getColor(displacedCoordinate) == getColor(coordinate)) {
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

    void print() {
        Console console = Console.getInstance();
        Message.HORIZONTAL_LINE.println();
        for (int i = 0; i <= Coordinate.ROWS; i++) {
            Message.VERTICAL_LINE.print();
            for (int j = 0; j < Coordinate.COLUMNS; j++) {
                if (i < Coordinate.ROWS) {
                    this.getColor(new Coordinate(i, j)).print();
                } else {
                    console.print(j + 1);
                }
                Message.VERTICAL_LINE.print();
            }
            console.println();
        }
        Message.HORIZONTAL_LINE.println();
    }

}
