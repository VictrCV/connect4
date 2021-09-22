package main;

import utils.RectangleBoundedCoordinate;

class Coordinate extends RectangleBoundedCoordinate {

    public static final int ROWS = 6;
    public static  final int COLUMNS = 7;

    Coordinate() {
        super();
    }

    Coordinate(int row, int column) {
        super(row, column);
    }

    @Override
    protected int getMaxRows() {
        return Coordinate.ROWS;
    }

    @Override
    protected int getMaxColumns() {
        return Coordinate.COLUMNS;
    }

    @Override
    protected String getErrorMessage() {
        return Error.WRONG_COLUMN.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinate other = (Coordinate) obj;
        if (!other.isNull()) {
            if (this.getRow() != other.getRow())
                return false;
            return this.getColumn() == other.getColumn();
        }
        return true;
    }

}
