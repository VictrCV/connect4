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

    public void read(String message) {
        assert message != null;

        this.coordinate = new ConcreteCoordinate();
        ConcreteCoordinate coordinate = (ConcreteCoordinate) this.coordinate;
        boolean error;
        do {
            coordinate.read(message);
            error = !this.isValid();
            if (error) {
                Console.getInstance().println(this.getErrorMessage());
            }
        } while (error);
    }

    protected abstract String getErrorMessage();

    public void random() {
        Random random = new Random(System.currentTimeMillis());
        this.coordinate = new ConcreteCoordinate(random.nextInt(this.getMaxRows()), random.nextInt(this.getMaxColumns()));
    }

    public int getRow() {
        assert !this.coordinate.isNull();

        return ((ConcreteCoordinate) this.coordinate).getRow();
    }

    public int getColumn() {
        assert !this.coordinate.isNull();

        return ((ConcreteCoordinate) this.coordinate).getColumn();
    }

}
