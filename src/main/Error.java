package main;

import utils.Console;

enum Error {

    NOT_EMPTY("The column is full"),
    WRONG_COLUMN("The column is wrong"),
    NULL;

    private String message;

    Error() {
    }

    Error(String message) {
        this.message = message;
    }

    void println() {
        if (!this.isNull()) {
            Console.getInstance().println(this.message);
        }
    }

    public boolean isNull() {
        return this == Error.NULL;
    }
}

