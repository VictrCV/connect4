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

    public GameState putToken(){
        Coordinate coordinate;
        Error error;
        do{
            coordinate = this.readColumn(Message.ENTER_COORDINATE_TO_PUT);
            error = this.isValidColumn(coordinate.getColumn());
        }while(!error.isNull());
        return this.board.putToken(coordinate.getColumn(), this.color);
    }

    private Coordinate readColumn(Message message){
        assert message != null;

        Coordinate coordinate = new Coordinate();
        coordinate.readColumn(message.toString());
        return coordinate;
    }

    private Error isValidColumn(int column) {
        Error error = Error.NULL;
        if (!this.board.isEmpty(new Coordinate(0, column))) {
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
