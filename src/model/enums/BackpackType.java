package model.enums;

public enum BackpackType {
    initial(12,true),
    big(24, true),
    deluxe(100, false),
    ;
    boolean is_limited;
    final int capacity;

    BackpackType(int capacity, boolean is_limited) {
        this.capacity = capacity;
        this.is_limited = is_limited;
    }
}
