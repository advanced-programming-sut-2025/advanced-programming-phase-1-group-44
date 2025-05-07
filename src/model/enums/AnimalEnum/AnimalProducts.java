package model.enums.AnimalEnum;

public enum AnimalProducts {
    egg(50),
    largeEgg(95),
    dockEgg(95),
    dockFeather(250),
    rabbitWool(340),
    rabbitLeg(565),
    dinasourEgg(350),
    
    milkCow(125),
    largeMilkCow(190),
    milkGoat(225),
    largeMilkGoat(345),
    sheetWool(340),
    truffle(625);

    private final int price;
    AnimalProducts (int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
