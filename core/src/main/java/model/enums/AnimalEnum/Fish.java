package model.enums.AnimalEnum;

import model.Food;
import model.enums.Season;

import java.util.ArrayList;

public enum Fish {
    Salmon(75, Season.autumn),
    Sardine(40, Season.autumn),
    Shad(60, Season.autumn),
    BlueDiscus(120, Season.autumn),
    MidnightCarp(150, Season.winter),
    Squid(80, Season.winter),
    Tuna(100, Season.winter),
    Perch(55, Season.winter),
    Flounder(100, Season.spring),
    Lionfish(100, Season.spring),
    Herring(30, Season.spring),
    Ghostfish(45, Season.spring),
    Tilapia(75, Season.summer),
    Dorado(100, Season.summer),
    Sunfish(30, Season.summer),
    RainbowTrout(65, Season.summer);

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
