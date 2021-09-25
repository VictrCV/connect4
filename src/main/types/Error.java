package main.types;

public enum Error {

    NOT_EMPTY("The column is full"),
    WRONG_COLUMN("The column is wrong"),
    NULL;

    private String message;

    Error() {
    }

    Error(String message) {
        this.message = message;
    }

    public boolean isNull() {
        return this == Error.NULL;
    }

    public String getMessage() {
        return this.message;
    }
}

