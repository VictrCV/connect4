package main;

public class Player {

    private Color color;
    private Board board;

    public Player(Color color, Board board){
        assert !color.isNull();
        assert board != null;

        this.color = color;
        this.board = board;
    }

    public void putToken(){
        Coordinate coordinate;
        Error error;
        do{
            coordinate = this.getCoordinate(Message.ENTER_COORDINATE_TO_PUT);
            error = this.getPutTokenError(coordinate);
        }while(!error.isNull());
        this.board.putToken(coordinate, this.color);
    }

    private Coordinate getCoordinate(Message message){
        assert message != null;

        Coordinate coordinate = new Coordinate();
        coordinate.read(message.toString());
        return coordinate;
    }

    private Error getPutTokenError(Coordinate coordinate) {
        assert coordinate != null;

        Error error = Error.NULL;
        if (!this.board.isEmpty(coordinate)) {
            error = Error.NOT_EMPTY;
        }
        error.println();
        return error;
    }

    public void printWinner(){
        Message.PLAYER_WIN.println(this.color.name());
    }

    public Color getColor(){
        return this.color;
    }
}
