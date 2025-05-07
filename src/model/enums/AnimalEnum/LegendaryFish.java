package model.enums.AnimalEnum;

import model.enums.Season;

public enum LegendaryFish {
    Legendary(5000, Season.SPRING),
    Glacierfish(1000, Season.WINTER),
    Angler(900, Season.FALL),
    Crimsonfish(1500, Season.SUMMER),
    ;
    private final int price;
    private final Season season;

    LegendaryFish(int price, Season season) {
        this.price = price;
        this.season = season;
    }

    public int getPrice() {
        return price;
    }

    public Season getSeason() {
        return season;
    }
}
