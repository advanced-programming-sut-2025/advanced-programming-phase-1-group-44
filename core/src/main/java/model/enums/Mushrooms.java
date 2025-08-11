package model.enums;

public enum Mushrooms {
    COMMON_MUSHROOM("Common Mushroom"),
    MOREL("Morel"),
    RED_MUSHROOM("Red Mushroom"),
    CHANTERELLE("Chanterelle"),
    PURPLE_MUSHROOM("Purple Mushroom");

    private final String name;

    Mushrooms(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
