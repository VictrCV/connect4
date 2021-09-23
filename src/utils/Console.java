package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Console {

    private static Console instance = new Console();

    public static Console getInstance() {
        return instance;
    }

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public String readString(String title) {
        String input = null;
        this.print(title);
        try {
            input = this.bufferedReader.readLine();
        } catch (Exception ex) {
        }
        return input;
    }

    public String readString() {
        return this.readString("");
    }

    public int readInt(String title) {
        int input = 0;
        boolean ok = false;
        do {
            try {
                input = Integer.parseInt(this.readString(title));
                ok = true;
            } catch (Exception ex) {
                this.printError("integer");
            }
        } while (!ok);
        return input;
    }

    public int readInt() {
        return this.readInt("");
    }

    public char readChar(String title) {
        char charValue = ' ';
        boolean ok = false;
        do {
            String input = this.readString(title);
            if (input.length() != 1) {
                this.printError("character");
            } else {
                charValue = input.charAt(0);
                ok = true;
            }
        } while (!ok);
        return charValue;
    }

    public void print(String string) {
        System.out.print(string);
    }

    public void print(int integer) {
        System.out.print(integer);
    }

    public void print(char character) {
        System.out.print(character);
    }

    public void println() {
        System.out.println();
    }

    public void println(String string) {
        this.print(string);
        this.println();
    }

    public void println(int integer) {
        this.print(integer);
        this.println();
    }

    public void printError(String format) {
        this.print("FORMAT ERROR! Enter a " + format + " formatted value.");
        this.println();
    }

}
