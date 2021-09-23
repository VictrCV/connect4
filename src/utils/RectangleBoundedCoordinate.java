package utils;

import java.util.Random;

public abstract class RectangleBoundedCoordinate {

    private Coordinate coordinate;

    public RectangleBoundedCoordinate() {
        this.coordinate = NullCoordinate.getInstance();
    }

    public boolean isNull() {
        return this.coordinate.isNull();
    }

    public RectangleBoundedCoordinate(int row, int column) {
        this.coordinate = new ConcreteCoordinate(row, column);

        assert this.isValid();
    }

    public void readColumn(String message) {
        assert message != null;

        this.coordinate = new ConcreteCoordinate();
        ConcreteCoordinate coordinate = (ConcreteCoordinate) this.coordinate;
        boolean error;
        do {
            coordinate.readColumn(message);
            System.out.println("antes");
            error = this.getColumnLimits().isIncluded(coordinate.getColumn());
            System.out.println("después");
            if (error) {
                Console.getInstance().println(this.getErrorMessage());
            }
        } while (error);
    }

    private boolean isValid() {
        assert !this.coordinate.isNull();

        ConcreteCoordinate concreteCoordinate = (ConcreteCoordinate) this.coordinate;
        return this.getRowLimits().isIncluded(concreteCoordinate.getRow())
                && this.getColumnLimits().isIncluded(concreteCoordinate.getColumn());
    }

    protected ClosedInterval getRowLimits() {
        return new ClosedInterval(0, this.getMaxRows() - 1);
    }

    protected ClosedInterval getColumnLimits() {
        return new ClosedInterval(0, this.getMaxColumns() - 1);
    }

    protected abstract int getMaxRows();

    protected abstract int getMaxColumns();

    public Direction getDirection(RectangleBoundedCoordinate coordinate) {
        return this.coordinate.getDirection(coordinate.coordinate);
    }

    protected abstract String getErrorMessage();

    public int getRow() {
        assert !this.coordinate.isNull();

        return ((ConcreteCoordinate) this.coordinate).getRow();
    }

    public int getColumn() {
        assert !this.coordinate.isNull();

        return ((ConcreteCoordinate) this.coordinate).getColumn();
    }

}
