package adts;

import java.util.Objects;

public class Position<T> {
    private Position<T> previousPosition;
    private Position<T> nextPosition;
    private T element;

    public Position(Position<T> previousPosition, Position<T> nextPosition, T element) {
        this.previousPosition = previousPosition;
        this.nextPosition = nextPosition;
        this.element = element;
    }

    public Position<T> getPreviousPosition() {
        return previousPosition;
    }

    public void setPreviousPosition(Position<T> previousPosition) {
        this.previousPosition = previousPosition;
    }

    public Position<T> getNextPosition() {
        return nextPosition;
    }

    public void setNextPosition(Position<T> nextPosition) {
        this.nextPosition = nextPosition;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position<?> position = (Position<?>) o;
        return Objects.equals(previousPosition, position.previousPosition) &&
                Objects.equals(nextPosition, position.nextPosition) &&
                Objects.equals(element, position.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(previousPosition, nextPosition, element);
    }
}
