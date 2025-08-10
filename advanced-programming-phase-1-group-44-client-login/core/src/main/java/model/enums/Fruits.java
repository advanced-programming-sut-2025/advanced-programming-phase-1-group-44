package model.enums;

public enum Fruits {
    COFFEE_BEAN("Coffee Bean"),
    STRAWBERRY("Strawberry"),
    BLUEBERRY("Blueberry"),
    HOT_PEPPER("Hot Pepper"),
    MELON("Melon"),
    STARFRUIT("Starfruit"),
    SUMMER_SQUASH("Summer Squash"),
    TOMATO("Tomato"),
    CRANBERRIES("Cranberries"),
    GRAPE("Grape"),
    SWEET_GEM_BERRY("Sweet Gem Berry"),
    ANCIENT_FRUIT("Ancient Fruit");

    private final String name;

    Fruits(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
