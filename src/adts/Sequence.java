package adts;

import interfaces.*;

public class Sequence<T> implements ISequence<T>{
    private Position<T> head;
    private Position<T> tail;
    private int size; //number of elements

    public Position<T> getHead() {
        return head;
    }

    public Position<T> getTail() {
        return tail;
    }

    public Sequence() {
        head = new Position<T>(null, null,null );
        tail = new Position<T>(null, null,null );
        head.setNextPosition(tail);
        tail.setPreviousPosition(head);
        this.size = 0;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Position<T> currentPosition = head;
        for (int i = 0; i <= index ; i++) {
            currentPosition = currentPosition.getNextPosition();
        }
        return currentPosition.getElement();
    }


    @Override
    public void set(int index, T object) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Position<T> currentPosition = head;
        for (int i = 0; i <= index ; i++) {
            currentPosition = currentPosition.getNextPosition();
        }
        currentPosition.setElement(object);
    }

    @Override
    public void addLast(T object) {
        Position<T> oldLastPosition = tail.getPreviousPosition();
        Position<T> newLastPosition = new Position<T>(oldLastPosition, tail, object);
        oldLastPosition.setNextPosition(newLastPosition);
        tail.setPreviousPosition(newLastPosition);
        size+=1;
    }

    @Override
    public T remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(); // could also happen when size == 0 which means that it's empty
        }
        Position<T> currentPosition = head;
        for (int i = 0; i <= index ; i++) {
            currentPosition = currentPosition.getNextPosition();
        }
        var prev = currentPosition.getPreviousPosition();
        var next = currentPosition.getNextPosition();
        prev.setNextPosition(next);
        next.setPreviousPosition(prev);
        return currentPosition.getElement();
    }

    @Override
    public T next(Position<T> position) {
        return position.getNextPosition().getElement();
    }

    @Override
    public T previous(Position<T> position) {
        return position.getPreviousPosition().getElement();
    }

    @Override
    public Position<T> atIndex(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Position<T> currentPosition = head;
        for (int i = 0; i <= index ; i++) {
            currentPosition = currentPosition.getNextPosition();
        }
        return currentPosition;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }
}
