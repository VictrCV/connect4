package utils;

public class ConcreteCoordinate implements Coordinate {

    protected int row;
    protected int column;

    protected ConcreteCoordinate() {
    }

    protected ConcreteCoordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + column;
        result = prime * result + row;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConcreteCoordinate other = (ConcreteCoordinate) obj;
        if (column != other.column)
            return false;
        return row == other.row;
    }

    @Override
    public String toString() {
        return "Coordinate (" + row + ", " + column + ")";
    }

}
