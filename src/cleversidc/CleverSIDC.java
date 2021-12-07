package cleversidc;

import adts.*;
import utils.Utilities;

public class CleverSIDC {
    // 2 main data structures
    private HashTable table;
    private Sequence<Entry> sequence;
    private Sequence<Integer> allKeys;
    private int threshold;


	public void setSIDCThreshold(int threshold) {
		if (threshold >= 100 && threshold <= 10000){
            this.sequence = new Sequence<Entry>();

        }
        else if (threshold >= 10000 &&threshold <= 500000) {
            this.table = new HashTable(threshold);
		}
		else {
            System.out.println("Error: Threshold must be between 100 and 500,000.");
            System.exit(0);
        }
        this.threshold = threshold;
	}


    private Sequence<Integer> sortKeysHashTable(){
        int[] keysUnsorted = table.keySet();
        return Utilities.bucketSort(keysUnsorted);

    }

    private Sequence<Integer> sortKeysSequence(){
        Sequence<Integer> keysUnsorted = new Sequence<Integer>();
        Position<Entry> p = this.sequence.getHead();
        p = p.getNextPosition();
        while(p.getElement() != null) {
            keysUnsorted.addLast(p.getElement().getKey());
        }

        return Utilities.bucketSort(keysUnsorted);
    }

    public Sequence<Integer> allKeys() {
        return this.allKeys;
    }

    public void add(int key, String value){
        if (threshold >= 100 && threshold <= 10000){
            sequence.addLast(new Entry(key, value));
            this.allKeys = sortKeysSequence();

        }
        else { //(threshold >= 10000 &&threshold <= 500000)
            table.put(key,value);
            this.allKeys = sortKeysHashTable();
        }

    }
    public String remove(int key){
        if (threshold >= 100 && threshold <= 10000){
            String value = null;
            Position<Entry> p = this.sequence.getHead();
            p = p.getNextPosition();
            while(p.getElement() != null) {
               if (p.getElement().getKey() == key){
                   value = p.getElement().getValue();
                   break;
               }
            }

            this.allKeys = sortKeysSequence();
            return value;
        }
        else { //(threshold >= 10000 &&threshold <= 500000)
            String value = table.remove(key);
            this.allKeys = sortKeysHashTable();
            return value;
        }

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

        Position<Integer> p = allKeys.getHead().getNextPosition();
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


