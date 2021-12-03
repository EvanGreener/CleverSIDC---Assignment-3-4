package interfaces;
import adts.Position;

public interface ISequence<T> {
    T get(int index);
    void set(int index, T object);
    void addLast(T object);
    T remove(int index);
    T next(Position<T> position);
    T previous(Position<T> position);
    int size();
    boolean isEmpty();

}
