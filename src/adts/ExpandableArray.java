package adts;


public class ExpandableArray {
    private Entry[] internalArray;
    private int size;
    private int maxCapacity;

    public ExpandableArray(int maxCapacity) {
        this.internalArray =  new Entry[maxCapacity];
        this.size = 0;
        this.maxCapacity = maxCapacity;
    }

    public void add(Entry element){
        internalArray[size] = element;
        size += 1;
        if (isFull()){
            doubleCapacity();
        }
    }

    public void remove(int index) {

    }

    private boolean isFull(){
        return size == maxCapacity;
    }

    private void doubleCapacity(){
        maxCapacity *= 2;
        var temp = new Entry[maxCapacity];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = internalArray[i];
        }
        internalArray = temp;
    }



}
