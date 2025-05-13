package model.enums.AnimalEnum;

public enum AnimalProductsEnum {
    egg(50, "egg"),
    largeEgg(95, "largeEgg"),
    duckEgg(95, "dockEgg"),
    duckFeather(250, "dockFeather"),
    rabbitWool(340, "rabbitWool"),
    rabbitLeg(565, "rabbitLeg"),
    dinasourEgg(350, "dinasourEgg"),
    
    milkCow(125, "milkCow"),
    largeMilkCow(190, "largeMilkCow"),
    milkGoat(225, "milkGoat"),
    largeMilkGoat(345, "largeMilkGoat"),
    sheepWool(340, "sheetWool"),
    truffle(625, "truffle");

    private final int price;
    private final String name;
    AnimalProductsEnum (int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }
    public String getName() {
        return name;
    }
}
