package model.enums;

public enum BackpackType {
    initial(12,true),
    big(24, true),
    deluxe(100, false),
    ;
    private boolean isLimited;
    private final int capacity;

    BackpackType(int capacity, boolean is_limited) {
        this.capacity = capacity;
        this.isLimited = is_limited;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isLimited() {
        return isLimited;
    }
}
