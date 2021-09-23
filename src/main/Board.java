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
        int i = Coordinate.ROWS - 1;
        do{
            coordinate = new Coordinate(i, column);
            error = this.isValidCoordinate(coordinate);
            i--;
        }while(!error.isNull());
        this.colors[i + 1][column] = color;
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

    public GameState isFinish(Coordinate coordinate){
        if (isConnect4(coordinate)){
            return GameState.CONNECT4;
        }
        if (isFull()){
            return GameState.DRAW;
        }
        return GameState.NOT_FINISH;
    }

    boolean isConnect4(Coordinate coordinate) {
        assert !coordinate.isNull();

        Direction[] directions = Direction.values();
        boolean connect4 = false;
        int i = 0;
        while(i < Direction.values().length - 1 && !connect4){
            connect4 = checkDirection(directions[i], coordinate);
            i++;
        }
        return connect4;
    }

    private boolean checkDirection(Direction direction, Coordinate coordinate){
        assert !coordinate.isNull();

        Coordinate originCoordinate = coordinate;
        int connectedTokens = 1;
        boolean isDirectionUp = true;
        boolean noMoreTokensToCheck = false;
        int j = 0;
        while(j < NUM_TOKENS_TO_WIN - 1 && connectedTokens < NUM_TOKENS_TO_WIN && !noMoreTokensToCheck){
            Coordinate nextCoordinate = getNextCoordinate(coordinate, direction, isDirectionUp);
            if (nextCoordinate.isValid() && this.getColor(coordinate) == this.getColor(nextCoordinate)){
                connectedTokens++;
                coordinate = nextCoordinate;
                j++;
            } else if(isDirectionUp){
                isDirectionUp = false;
                coordinate = originCoordinate;
                j = 0;
            } else {
                noMoreTokensToCheck = true;
            }
        }
        return connectedTokens == NUM_TOKENS_TO_WIN;
    }

    private Coordinate getNextCoordinate(Coordinate coordinate, Direction direction, boolean isDirectionUp){
        int nextRow;
        int nextColumn;
        if (isDirectionUp){
            nextRow = coordinate.getRow() + direction.getX();
            nextColumn = coordinate.getColumn() + direction.getY();
        } else {
            nextRow = coordinate.getRow() - direction.getX();
            nextColumn = coordinate.getColumn() - direction.getY();
        }
        return new Coordinate(nextRow, nextColumn);
    }

    private boolean isFull(){
        boolean full;
        int i = 0;
        do{
            full = isOccupied(new Coordinate(0, i));
            i++;
        }while(i < Coordinate.ROWS && full);
        return full;
    }

    void print() {
        Console console = Console.getInstance();
        Message.HORIZONTAL_LINE.println();
        for (int i = 0; i <= Coordinate.ROWS; i++) {
            Message.VERTICAL_LINE.print();
            for (int j = 0; j < Coordinate.COLUMNS; j++) {
                if(i < Coordinate.ROWS){
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
