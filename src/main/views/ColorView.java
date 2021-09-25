package main.views;

import main.types.Color;
import utils.views.Console;

public class ColorView {

    public void print(Color color) {
        String string = color.name();
        if (color.isNull()) {
            string = " ";
        }
        Console.getInstance().print(string);
    }
}
