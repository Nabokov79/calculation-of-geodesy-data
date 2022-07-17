package model;

public class ControlPoints {

    private final String key;
    private final int value;

    public ControlPoints(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ControlPoints{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
