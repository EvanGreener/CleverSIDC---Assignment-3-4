package cleversidc;

import adts.*;
import utils.Utilities;

public class CleverSIDC {
    private HashTable table;
    private Sequence<Integer> allKeys;

    public CleverSIDC(int size){
        this.table = new HashTable(size);
    }

    public void SetSIDCThreshold(int size){

    }

    private Sequence<Integer> sortKeys(){
        int[] keysUnsorted = table.keySet();
        int[] keysSorted = Utilities.bucketSort(keysUnsorted);

        Sequence<Integer> keysSortedS = new Sequence<Integer>();
        for (int key : keysSorted){
            keysSortedS.addLast(key);
        }
        return keysSortedS;
    }

    public Sequence<Integer> allKeys() {
        return this.allKeys;
    }

    public void add(int key, String value){
        table.put(key,value);
        this.allKeys = sortKeys();
    }
    public String remove(int key){
        String value = table.remove(key);
        this.allKeys = sortKeys();
        return value;
    }

    public String getValues(int key){
        return table.get(key);
    }

    public int nextKey(int key){
        Position<Integer> position = this.allKeys.atIndex(key);
        return position.getNextPosition().getElement();
    }

    public int prevKey(int key){
        Position<Integer> position = this.allKeys.atIndex(key);
        return position.getPreviousPosition().getElement();
    }

    public int rangeKey(int key1, int key2){
        int indexKey1 = -1;
        int indexKey2 = -1;

        Position<Integer> p = allKeys.getHead();
        for(int i = 0; i < allKeys.size(); i++) {
            int currentKey = p.getElement();
            if (currentKey == key1){
                indexKey1 = i;
            }
            else if(currentKey == key2){
                indexKey2 = i;
            }

            if (indexKey1 != -1 && indexKey2 != -1){
                break;
            }
        }
        return Math.abs(indexKey1 - indexKey2);
    }

    public int generate(){
        return 0;
    }



}
