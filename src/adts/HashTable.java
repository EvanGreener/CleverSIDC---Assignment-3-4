package adts;

import utils.Utilities;
import interfaces.*;

import java.util.Arrays;

public class HashTable implements IHashTable {
    Entry[] internalArray;
    int size;
    int prime; // Also equal to N, i.e. the capacity of the table
    final float loadFactor = 0.33f; // For open addressing data hash tables, 1/3rd is a good choice

    public HashTable(int prime) {
        this.internalArray = new Entry[prime];
        this.size=0;
        this.prime = prime;
    }

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

        size += 1;

        if ((float)size/prime >= loadFactor){
            rehashAllKeys(); //Also increases capacity and adjusts hash function
        }
    }

    // Doubles the capacity of the array and rehashes all the keys
    public void rehashAllKeys() {
        int oldPrime = internalArray.length;
        prime = Utilities.sieveOfEratosthenes(prime *2);

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
    // adjacent cell whose hash is earlier or equal to the hash of the cell being emptied.
    // This is a better alternative to using the lazy deletion strategy which utilizes
    // the AVAILABLE object adds to the load factor and can increase the time of get(key).
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
            // Linear probing algorithm
            while (c != null){
                c = internalArray[index];
                if (c == null){
                    continue;
                }
                else if (c.getKey() == key) {
                    value = c.getValue();
                    cellToBeEmptied = index;
                }
                else if (cellToBeEmptied != -1 && hash(c.getKey()) <= cellToBeEmptied){
                    break;
                }
                index = (index + 1) % N;
            } ;
        }

        if (cellToBeEmptied != -1) {
            if (internalArray[index] == null){
                int lastAdjacentCell = index == 0 ? prime - 1 : index - 1; // -1 since array[index] is null
                internalArray[cellToBeEmptied] = internalArray[lastAdjacentCell];
                internalArray[lastAdjacentCell] = null;
            }
            else{
                int lastAdjacentCell = index;
                internalArray[cellToBeEmptied] = internalArray[lastAdjacentCell];
                internalArray[lastAdjacentCell] = null;
            }

            return value;
        }
        else {
            return null;
        }
    }

    // Since the keys are already integers, a simple MAD compression is used to hash the key
    @Override
    public int hash(int key) {
        int a = prime -1;
        int b = prime -2;
        return (a*key + b) % prime;
    }

    @Override
    public int[] keySet() {
        var allKeys = new int[prime];

        int i = 0;
        for (Entry e : internalArray ) {
            if (e != null){
                allKeys[i] = e.getKey();
                i++;
            }

        }

        return Arrays.copyOf(allKeys, i);
    }

}


