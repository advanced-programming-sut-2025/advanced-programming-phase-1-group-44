package model.enums;

public enum FishingPoleType {
    learning(0, 25, 8, false),
    bambo(0, 500 , 8,  true),
    fiberglass(2, 1800, 6,  true),
    iridium(4, 7500, 4, false),
    ;

    public final int level, price, cost;
    public final boolean onlyWilly;

    FishingPoleType(int level, int price, int cost, boolean onlyWilly) {
        this.level = level;
        this.price = price;
        this.cost = cost;
        this.onlyWilly = onlyWilly;
    }
}
