package interfaces;
import adts.Position;

public interface ISequence<T> {
    T get(int index);
    void set(int index, T object);
    void addLast(T object);
    T remove(int index);
    T next(Position<T> position);
    T previous(Position<T> position);
    Position<T> atIndex(int index);
    int size();
    boolean isEmpty();
    Position<T> first();
    Position<T> last();

}
