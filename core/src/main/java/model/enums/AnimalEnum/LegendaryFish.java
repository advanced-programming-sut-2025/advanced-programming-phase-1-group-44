package model.enums.AnimalEnum;

import model.Food;
import model.enums.Season;

public enum LegendaryFish {
    Legendary(5000, Season.spring),
    Glacierfish(1000, Season.winter),
    Angler(900, Season.autumn),
    Crimsonfish(1500, Season.summer),
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
    public Food getItem(){
        return new Food(this.name(), this.price);
    }
    public static Food getFish(Season season){
        for (LegendaryFish fish : LegendaryFish.values()) {
            if(fish.season.equals(season))
                return fish.getItem();
        }
        return null;
    }
}
