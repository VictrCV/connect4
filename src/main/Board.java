package main;

import java.util.ArrayList;
import java.util.List;

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
        assert coordinate != null;

        Error error = Error.NULL;
        if (!this.isEmpty(coordinate)) {
            error = Error.NOT_EMPTY;
        }
        error.println();
        return error;
    }

    private Color getColor(Coordinate coordinate) {
        assert !coordinate.isNull();

        return this.colors[coordinate.getRow()][coordinate.getColumn()];
    }

    boolean isOccupied(Coordinate coordinate, Color color) {
        return this.getColor(coordinate) == color;
    }

    boolean isEmpty(Coordinate coordinate) {
        return this.isOccupied(coordinate, Color.NULL);
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
        int i = 0;
        boolean connect4 = false;
        while(i < Direction.values().length - 1 && !connect4){
            int connectedTokens = 1;
            int j = 0;
            boolean changeDirection = false;
            while(j < NUM_TOKENS_TO_WIN - 1 && connectedTokens < NUM_TOKENS_TO_WIN && !changeDirection){
                int nextRow = coordinate.getRow() + directions[i].getX();
                int nextColumn = coordinate.getColumn() + directions[i].getY();
                Coordinate nextCoordinate = new Coordinate(nextRow, nextColumn);
                if (this.getColor(coordinate) == this.getColor(nextCoordinate)){
                    connectedTokens++;
                } else {
                    changeDirection = true;
                }
            }

            int j = 0;
            boolean changeDirection = false;
            while(j < NUM_TOKENS_TO_WIN - 1 && connectedTokens < NUM_TOKENS_TO_WIN && !changeDirection){
                int nextRow = coordinate.getRow() - directions[i].getX();
                int nextColumn = coordinate.getColumn() - directions[i].getY();
                Coordinate nextCoordinate = new Coordinate(nextRow, nextColumn);
                if (this.getColor(coordinate) == this.getColor(nextCoordinate)){
                    connectedTokens++;
                } else {
                    changeDirection = true;
                }
            }
        }

    }

    private boolean isFull(){
        boolean full;
        int i = 0;
        do{
            full = !isEmpty(new Coordinate(0, i));
            i++;
        }while(i < Coordinate.ROWS && full);
        return full;
    }

    List<Coordinate> getCoordinates(Color color) {
        assert !color.isNull();

        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < Coordinate.ROWS; i++) {
            for (int j = 0; j < Coordinate.COLUMNS; j++) {
                if (this.getColor(new Coordinate(i,j)) == color) {
                    coordinates.add(new Coordinate(i, j));
                }
            }
        }
        return coordinates;
    }

    void print() {
        Message.HORIZONTAL_LINE.println();
        for (int i = 0; i < Coordinate.ROWS; i++) {
            Message.VERTICAL_LINE.print();
            for (int j = 0; j < Coordinate.COLUMNS; j++) {
                this.getColor(new Coordinate(i, j)).print();
                Message.VERTICAL_LINE.print();
            }
            Console.getInstance().println();
        }
        Message.HORIZONTAL_LINE.println();
    }

}
