package model.enums.AnimalEnum;

import model.Food;
import model.Item;
import model.enums.Season;

import java.util.ArrayList;

public enum Fish {
    Salmon(75, Season.FALL),
    Sardine(40, Season.FALL),
    Shad(60, Season.FALL),
    BlueDiscus(120, Season.FALL),
    MidnightCarp(150, Season.WINTER),
    Squid(80, Season.WINTER),
    Tuna(100, Season.WINTER),
    Perch(55, Season.WINTER),
    Flounder(100, Season.SPRING),
    Lionfish(100, Season.SPRING),
    Herring(30, Season.SPRING),
    Ghostfish(45, Season.SPRING),
    Tilapia(75, Season.SUMMER),
    Dorado(100, Season.SUMMER),
    Sunfish(30, Season.SUMMER),
    RainbowTrout(65, Season.SUMMER);

    private final int price;
    private final Season season;

    Fish(int price, Season season) {
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
    public static ArrayList<Food> getFishes(Season season){
        ArrayList<Food> fishes = new ArrayList<>();
        for (Fish fish : Fish.values()) {
            if(fish.season.equals(season)){
                fishes.add(fish.getItem());
            }
        }
        return fishes;
    }
}
