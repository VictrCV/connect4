package utils;

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

    public boolean isValid() {
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
