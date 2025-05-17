package model.enums;

import java.util.ArrayList;
import java.util.List;

import model.DateTime;
import model.Item;

public enum ArtisansProducts {
    honey("honey", 75, 4*13, new ArrayList<Item>(), 350),
    cheese("cheese", 100, 3, new ArrayList<Item>(List.of(new Item("milkCow", 1), new Item("largeMilkCow", 1))), 230), 
    goat_cheese("goat cheese", 100, 3, new ArrayList<Item>(List.of(new Item("milkGoat", 1), new Item("largeMilkGoat", 1))), 400),
    beer("beer", 50, 24, new ArrayList<Item>(List.of(new Item("wheat", 1))), 200),
    vinegar("vinegar", 13, 10, new ArrayList<Item>(List.of(new Item("rice", 1))), 100),
    coffee("coffee", 75, 2, new ArrayList<Item>(List.of(new Item("coffeeBean", 5))), 150),
    juice("juice", 0, 96, new ArrayList<Item>(List.of(new Item("vegetable", 1))), 0),
    mead("mead", 100, 10, new ArrayList<Item>(List.of(new Item("honey", 1))), 300),
    pale_ale("pale ale", 50, 72, new ArrayList<Item>(List.of(new Item("hops", 1))), 300),
    wine("wine", 0, 168, new ArrayList<Item>(List.of(new Item("fruit", 1))), 0),
    cloth("cloth", 0, 4, new ArrayList<Item>(List.of(new Item("wool", 1))), 470),
    mayonnaise("mayonnaise", 50, 3, new ArrayList<Item>(List.of(new Item("egg", 1), new Item("largeEgg", 1))), 190),
    duck_mayonnaise("duck mayonnaise", 75, 3, new ArrayList<Item>(List.of(new Item("duckEgg", 1))), 37),
    dinosaur_mayonnaise("dinosaur mayonnaise", 125, 3, new ArrayList<Item>(List.of(new Item("dinosaurEgg", 1))), 800),
    truffle_oil("truffle oil", 38, 6, new ArrayList<Item>(List.of(new Item("truffle", 1))), 1065),
    oil("oil", 13, 6, new ArrayList<Item>(List.of(new Item("corn", 1), new Item("sunflowerSeeds", 1), new Item("sunflower", 1))), 100),
    pickles("pickles", 0, 6, new ArrayList<Item>(List.of(new Item("vegetable", 1))), 0),
    jelly("jelly", 0, 72, new ArrayList<Item>(List.of(new Item("fruit", 1))), 0),
    smoked_fish("smoked fish", 0, 1, new ArrayList<Item>(List.of(new Item("fish", 1), new Item("coal", 1))), 0),
    metal_bar("metal bar", 0, 4, new ArrayList<Item>(List.of(new Item("ore", 5), new Item("coal", 1))), 0);

    private final String name;
    private final int energy;
    private final int hours, price;
    private final List<Item> ingrediendts;

    ArtisansProducts(String name, int energy, int hours, List<Item> ingredients, int price) {
        this.name = name;
        this.energy = energy;
        this.hours = hours;
        this.price = price;
        this.ingrediendts = ingredients;
    }



}
