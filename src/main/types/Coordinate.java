package main.types;

import utils.models.Direction;
import utils.models.RectangleBoundedCoordinate;

public class Coordinate extends RectangleBoundedCoordinate {

    public static final int ROWS = 6;
    public static final int COLUMNS = 7;

    public Coordinate(int row, int column) {
        super(row, column);
    }

    public Coordinate displace(Direction direction, int magnitude) {
        int displacedRow = this.getRow() - direction.getX() * magnitude;
        int displacedColumn = this.getColumn() - direction.getY() * magnitude;
        return new Coordinate(displacedRow, displacedColumn);
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
