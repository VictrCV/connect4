package main;

import utils.Console;

enum Message {
    TITLE("--- CONNECT 4 ---"),
    HORIZONTAL_LINE("---------------"),
    VERTICAL_LINE(" | "),
    ENTER_COORDINATE_TO_PUT(" enter a column to put a token: "),
    PLAYER_WIN("#player player: You win!!! :-)"),
    DRAW("It's a draw, losers!");

    private String message;

    Message(String message) {
        this.message = message;
    }

    void print() {
        Console.getInstance().print(this.message);
    }

    void println() {
        Console.getInstance().println(this.message);
    }

    void println(String player) {
        assert this == Message.PLAYER_WIN;

        Console.getInstance().println(this.message.replaceAll("#player", "" + player));
    }

    @Override
    public String toString() {
        return message;
    }
}
