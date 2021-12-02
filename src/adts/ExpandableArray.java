package adts;

public class ExpandableArray<T> {
    private T[] internalArray;
    private int size;
    private int maxCapacity;

    public ExpandableArray(T[] internalArray, int size) {
        this.internalArray = internalArray;
        this.size = size;
        this.maxCapacity = internalArray.length;
    }

    private boolean isFull(){
        return size == maxCapacity;
    }

    private void doubleCapacity(){

    }

    public void add(T element){
        internalArray[size] = element;
        size +=1;

    }

}
