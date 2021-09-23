package main;

import utils.Console;

enum Color {

    X,
    O,
    NULL;

    static Color get(int ordinal) {
        assert ordinal >= 0 && ordinal < Color.NULL.ordinal();

        return Color.values()[ordinal];
    }

    void print() {
        String string = this.name();
        if (this.isNull()) {
            string = " ";
        }
        Console.getInstance().print(string);
    }

    boolean isNull() {
        return this == Color.NULL;
    }
}
