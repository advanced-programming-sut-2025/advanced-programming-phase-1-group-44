package model.enums;

public enum MaterialType {
    normal(0),
    copper(1),
    iron(2),
    gold(3),
    iridium(4),
    ;

    final int hardness;

    MaterialType(int hardness) {
        this.hardness = hardness;
    }
}
