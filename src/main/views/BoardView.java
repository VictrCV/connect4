package main.views;

import main.models.Board;
import main.types.Coordinate;
import utils.views.Console;

public class BoardView {

    public void print(Board board) {
        Console console = Console.getInstance();
        ColorView colorView = new ColorView();
        Message.HORIZONTAL_LINE.println();
        for (int i = 0; i <= Coordinate.ROWS; i++) {
            Message.VERTICAL_LINE.print();
            for (int j = 0; j < Coordinate.COLUMNS; j++) {
                if (i < Coordinate.ROWS) {
                    colorView.print(board.getColor(new Coordinate(i, j)));
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
