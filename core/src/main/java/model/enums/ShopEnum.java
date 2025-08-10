package model.enums;

public enum ShopEnum {
    blacksmith("Clint", 9, 16),
    jojaMart("Morris", 9, 11),
    pierresGeneralStore("Pierre", 9, 17),
    carpenter("Robin", 9, 17),
    fishShop("Willy", 9, 17),
    marniesRanch("Marnie", 9, 16),
    stardropSaloon("Gus", 12, 24);

    private final String owner;
    private final int openingHour;
    private final int closingHour;

    ShopEnum(String owner, int openingHour, int closingHour) {
        this.owner = owner;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    public String getOwner() {
        return owner;
    }

    public int getOpeningHour() {
        return openingHour;
    }

    public int getClosingHour() {
        return closingHour;
    }
}
