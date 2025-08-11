package model.enums;

import java.util.ArrayList;
import java.util.List;

import model.DateTime;
import model.Item;

public enum ArtisansProducts {
    honey("honey", 75, 4*13, List.of(), List.of(), 350, 0, 0),
    cheese("cheese", 100, 3, List.of(AllItems.milkCow, AllItems.largeMilkCow), List.of(), 230, 0, 0), 
    goat_cheese("goat cheese", 100, 3, List.of(AllItems.milkGoat, AllItems.largeMilkGoat), List.of(), 400, 0, 0),
    beer("beer", 50, 24, List.of(AllItems.Wheat), List.of(), 200, 0, 0),
    vinegar("vinegar", 13, 10, List.of(AllItems.rice), List.of(), 100, 0, 0),
    coffee("coffee", 75, 2, List.of(AllItems.coffeeBean, AllItems.coffeeBean, AllItems.coffeeBean, AllItems.coffeeBean, AllItems.coffeeBean), List.of(), 150, 0, 0),
    juice("juice", 0, 96, List.of(), List.of(AllItems.vegetable), 0, 2.25, 2),
    mead("mead", 100, 10, List.of(AllItems.honey), List.of(), 300, 0, 0),
    pale_ale("pale ale", 50, 72, List.of(AllItems.hops), List.of(), 300, 0, 0),
    wine("wine", 0, 168, List.of(), List.of(AllItems.fruit), 0, 3, 1.75),
    cloth("cloth", 0, 4, List.of(AllItems.wool), List.of(), 470, 0, 0),
    mayonnaise("mayonnaise", 50, 3, List.of(AllItems.egg, AllItems.largeEgg), List.of(), 190, 0, 0),
    duck_mayonnaise("duck mayonnaise", 75, 3, List.of(AllItems.duckEgg), List.of(), 37, 0, 0),
    dinosaur_mayonnaise("dinosaur mayonnaise", 125, 3, List.of(AllItems.dinasourEgg), List.of(), 800, 0, 0),
    truffle_oil("truffle oil", 38, 6, List.of(AllItems.truffle), List.of(), 1065, 0, 0),
    oil("oil", 13, 6, List.of(AllItems.corn, AllItems.sunflowerSeeds, AllItems.sunflower), List.of(), 100, 0, 0),
    pickles("pickles", 0, 6, List.of(), List.of(AllItems.vegetable), 50, 2, 1.75),
    jelly("jelly", 0, 72, List.of(), List.of(AllItems.fruit), 50, 2, 2),
    smoked_fish("smoked fish", 0, 1, List.of(AllItems.fish), List.of(AllItems.coal), 0, 2, 1.5),
    metal_bar("metal bar", 0, 4, List.of(AllItems.ore), List.of(AllItems.coal), 0, 0, 0),

    dried_mushrooms("dried mushrooms", 50, -1, List.of(AllItems.mushroom, AllItems.mushroom, AllItems.mushroom, AllItems.mushroom, AllItems.mushroom), List.of(), 0, 7.5, 0), // price is calculated
    dried_fruit("dried fruit", 75, -1, List.of(), List.of(AllItems.fruit), 25, 7.5, 0), // 5 of any fruit except grapes, price is calculated
    raisins("raisins", 125, -1, List.of(AllItems.grapes, AllItems.grapes, AllItems.grapes, AllItems.grapes, AllItems.grapes), List.of(), 600, 0, 0),
    coal("coal", 0, 1, List.of(AllItems.wood, AllItems.wood, AllItems.wood, AllItems.wood, AllItems.wood, AllItems.wood, AllItems.wood, AllItems.wood, AllItems.wood, AllItems.wood), List.of(), 50, 0, 0);

    private final String name;
    private final int energy;
    private final int hours, price;
    private final double priceMultiplier;
    private final double energyMultiplier;
    // must have all of these
    private final List<AllItems> mustIngredients;
    // must have at least one of these
    private final List<AllItems> oneOfIngredients;

    ArtisansProducts(String name, int energy, int hours, List<AllItems> mustIngredients, List<AllItems> oneOfIngredients, int price, double priceMultiplier, double energyMultiplier) {
        this.name = name;
        this.energy = energy;
        this.hours = hours;
        this.price = price;
        this.mustIngredients = mustIngredients;
        this.oneOfIngredients = oneOfIngredients;
        this.priceMultiplier = priceMultiplier;
        this.energyMultiplier = energyMultiplier;
    }
    
    public int getHours() {
        return hours;
    }
    public int getEnergy() {
        return energy;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }

    public List<AllItems> getMustIngredients() {
        return mustIngredients;
    }

    public List<AllItems> getOrIngredients() {
        return oneOfIngredients;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public double getEnergyMultiplier() {
        return energyMultiplier;
    }

    static public ArtisansProducts getArtisanProductByName(String name) {
        for (ArtisansProducts product : ArtisansProducts.values()) {
            if (product.name.equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
}
