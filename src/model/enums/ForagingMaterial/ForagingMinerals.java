package model.enums.ForagingMaterial;

public enum ForagingMinerals {
    Quartz(25),
    EarthCrystal(50),
    FrozenTear(75),
    FireQuartz(100),
    Emerald(250),
    Aquamarine(180),
    Ruby(250),
    Amethyst(100),
    Topaz(80),
    Jade(200),
    Diamond(750),
    PrismaticShard(2000),
    Copper(5),
    Iron(10),
    Gold(25),
    Iridium(100),
    Coal(15);
    private final int price;

    ForagingMinerals(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
