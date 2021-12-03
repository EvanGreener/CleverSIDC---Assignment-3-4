package adts;

import utils.SieveOfEratosthenes;

class Entry {
    int key;
    String value;

    public Entry(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}

interface IHashTable {
    String get(int key);
    String remove(int key);
    void put(int key, String value);
    int[] keysSorted();
    int hash(int key);

}

public class HashTable implements IHashTable {
    Entry[] internalArray;
    int size;
    int prime; // Also equal to N, i.e. the capacity of the table
    final float loadFactor = 0.33f;

    @Override
    public String get(int key) {
        int index = hash(key);
        int N = prime;
        int p = 0;

        do {
            Entry c = internalArray[index];
            if (c == null){
                return null;
            }
            else if (c.getKey() == key){
                return c.getValue();
            }
            else {
                index = (index + 1) % N;
                p += 1;
            }
        } while (p != N);
        return null;
    }

    @Override
    public void put(int key, String value) {
        int index = hash(key);
        int N = internalArray.length;
        int p = 0;

        do {
            Entry c = internalArray[index];
            if (c == null){
                internalArray[index] = new Entry(key,value);
                break;
            }
            else {
                index = (index + 1) % N;
                p += 1;
            }
        } while (p != N);

        if ((float)size/prime >= loadFactor){
            rehashAllKeys();
        }
    }

    // Doubles the capacity of the array and rehashes all the keys
    private void rehashAllKeys() {
        int oldPrime = internalArray.length;
        prime = SieveOfEratosthenes.sieveOfEratosthenes(prime *2);

        var newArray = new Entry[prime];
        for (Entry c : internalArray) {
            if (c != null) {
                //Inserting into array using linear probing, similar to put(k,v)
                int newHash = hash(c.getKey());
                int p = 0;
                do {
                    Entry d = newArray[newHash];
                    if (d == null) {
                        newArray[newHash] = c;
                        break;
                    } else {
                        newHash = (newHash + 1) % prime;
                        p += 1;
                    }
                } while (p != prime);
            }
        }

        internalArray = newArray;
    }

    // This remove algorithm uses a strategy where the emptied cell gets replaced by the
    // last adjacent cell that's not empty as opposed to using the special AVAILABLE
    // object. The lazy deletion strategy which utilizes the AVAILABLE object adds to the
    // load factor and can increase the time of get(key).
    @Override
    public String remove(int key) {
        int index = hash(key);
        int N = internalArray.length;
        String value = null;

        Entry c = internalArray[index];
        int cellToBeEmptied = -1;
        if (c == null){
            return null;
        }
        else{
            while (c != null){
                c = internalArray[index];
                if (c.getKey() == key) {
                    value = c.getValue();
                    cellToBeEmptied = index;
                }
                else {
                    index = (index + 1) % N;
                }
            }
        }

        if (cellToBeEmptied != -1) {
            int lastAdjacentCell = index -1; // -1 since index at this point is null
            internalArray[cellToBeEmptied] = internalArray[lastAdjacentCell];
            internalArray[lastAdjacentCell] = null;
            return value;
        }
        else {
            return null;
        }
    }

    @Override
    public int[] keysSorted() {
        var keys = new int[9];
        for (Entry e : internalArray) {

        }
        return keys;
    }

    // Since the keys are already integers, a simple MAD compressions is used to hash the key
    @Override
    public int hash(int key) {
        // Making a & b less than 'prime' it ensures that a % N is never 0
        // which is essential for MAD compression
        int a = prime -1;
        int b = prime -2;

        return (a*key + b) % prime;


    }
}


