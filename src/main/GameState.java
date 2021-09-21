package main;

public enum GameState {

    NOT_FINISH,
    CONNECT4,
    DRAW;

    static GameState get(int ordinal){
        assert ordinal >= 0 && ordinal < Color.NULL.ordinal();

        return GameState.values()[ordinal];
    }
}
