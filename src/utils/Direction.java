package utils;

public enum Direction {

    VERTICAL(0,1),
    HORIZONTAL(1,0),
    MAIN_DIAGONAL(1,1),
    INVERSE_DIAGONAL(1, -1),
    NULL(0,0);

    private final int x;
    private final int y;

    Direction(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
