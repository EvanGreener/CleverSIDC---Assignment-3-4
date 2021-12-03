package adts;

public class Entry {
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
