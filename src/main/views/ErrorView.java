package main.views;

import utils.views.Console;
import main.types.Error;

public class ErrorView {

    public void println(Error error) {
        if (!error.isNull()) {
            Console.getInstance().println(error.getMessage());
        }
    }
}
