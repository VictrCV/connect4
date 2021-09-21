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

    void putToken(Coordinate coordinate, Color color) {
        assert !coordinate.isNull();

        this.colors[coordinate.getRow()][coordinate.getColumn()] = color;
    }

    private Color getColor(Coordinate coordinate) {
        assert !coordinate.isNull();

        return this.colors[coordinate.getRow()][coordinate.getColumn()];
    }

    boolean isOccupiedByTheSameColor(Coordinate coordinate, Color color) {
        return this.getColor(coordinate) == color;
    }

    boolean isEmpty(Coordinate coordinate) {
        return this.isOccupiedByTheSameColor(coordinate, Color.NULL);
    }

    public GameState isFinish(Color color){
        if isConnect4(color){
            return GameState.CONNECT4
        } else {

        }
    }

    boolean isConnect4(Color color) {
        assert !color.isNull();

        List<Direction> directions = this.getDirections(color);

        if (directions.size() < NUM_TOKENS_TO_WIN) {
            return false;
        }

        int inLineTokens = 0;
        int i = 0;

        do{
            if (directions.get(i) == directions.get(i + 1)) {
                inLineTokens++;
            } else {
                inLineTokens = 0;
            }
            i++;
        }while(inLineTokens < 4 && i < directions.size() - 1);

        return inLineTokens == 4;
    }

    private List<Direction> getDirections(Color color) {
        assert !color.isNull();

        List<Direction> directions = new ArrayList<>();
        List<Coordinate> coordinates = this.getCoordinates(color);
        if(!coordinates.isEmpty()){
            for (int i = 0; i < coordinates.size() - 1; i++) {
                directions.add(coordinates.get(i).getDirection(coordinates.get(i + 1)));
            }
        }
        return directions;
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
