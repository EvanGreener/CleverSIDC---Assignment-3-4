package interfaces;

public interface IHashTable {
    String get(int key);
    String remove(int key);
    void put(int key, String value);
    int hash(int key);
    Iterable<Integer> keySet();
}
