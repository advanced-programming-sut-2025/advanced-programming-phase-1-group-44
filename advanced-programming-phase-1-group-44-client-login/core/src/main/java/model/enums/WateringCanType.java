package model.enums;

public enum WateringCanType {
    copper(55),
    iron(70),
    gold(85),
    iridium(100),
    ;
    final int capacity;

    WateringCanType(int capacity) {
        this.capacity = capacity;
    }
}
