package model.enums;

import java.util.Map;
import java.util.HashMap;

public enum Recipe {
    FRIED_EGG("Fried Egg", Map.of("Egg", 1), 50, "", "Starter", 35),
    BAKED_FISH("Baked Fish", Map.of("Sardine", 1, "Salmon", 1, "Wheat", 1), 75, "", "Starter", 100),
    SALAD("Salad", Map.of("Leek", 1, "Dandelion", 1), 113, "", "Starter", 110),
    OMELET("Omelet", Map.of("Egg", 1, "Milk", 1), 100, "", "Stardrop Saloon", 125),
    PUMPKIN_PIE("Pumpkin Pie", Map.of("Pumpkin", 1, "Wheat Flour", 1, "Milk", 1, "Sugar", 1), 225, "", "Stardrop Saloon", 385),
    SPAGHETTI("Spaghetti", Map.of("Wheat Flour", 1, "Tomato", 1), 75, "", "Stardrop Saloon", 120),
    PIZZA("Pizza", Map.of("Wheat Flour", 1, "Tomato", 1, "Cheese", 1), 150, "", "Stardrop Saloon", 300),
    TORTILLA("Tortilla", Map.of("Corn", 1), 50, "", "Stardrop Saloon", 50),
    MAKI_ROLL("Maki Roll", Map.of("Any Fish", 1, "Rice", 1, "Fiber", 1), 100, "", "Stardrop Saloon", 220),
    TRIPLE_SHOT_ESPRESSO("Triple Shot Espresso", Map.of("Coffee", 3), 200, "Max Energy + 100 (5 hours)", "Stardrop Saloon", 450),
    COOKIE("Cookie", Map.of("Wheat Flour", 1, "Sugar", 1, "Egg", 1), 90, "", "Stardrop Saloon", 140),
    HASH_BROWNS("Hash Browns", Map.of("Potato", 1, "Oil", 1), 90, "Farming (5 hours)", "Stardrop Saloon", 120),
    PANCAKES("Pancakes", Map.of("Wheat Flour", 1, "Egg", 1), 90, "Foraging (11 hours)", "Stardrop Saloon", 80),
    FRUIT_SALAD("Fruit Salad", Map.of("Blueberry", 1, "Melon", 1, "Apricot", 1), 263, "", "Stardrop Saloon", 450),
    RED_PLATE("Red Plate", Map.of("Red Cabbage", 1, "Radish", 1), 240, "Max Energy +50 (3 hours)", "Stardrop Saloon", 400),
    BREAD("Bread", Map.of("Wheat Flour", 1), 50, "", "Stardrop Saloon", 60),
    SALMON_DINNER("Salmon Dinner", Map.of("Salmon", 1, "Amaranth", 1, "Kale", 1), 125, "", "Leah reward", 300),
    VEGETABLE_MEDLEY("Vegetable Medley", Map.of("Tomato", 1, "Beet", 1), 165, "", "Foraging Level 2", 120),
    FARMERS_LUNCH("Farmer's Lunch", Map.of("Omelet", 1, "Parsnip", 1), 200, "Farming (5 hours)", "Farming level 1", 150),
    SURVIVAL_BURGER("Survival Burger", Map.of("Bread", 1, "Carrot", 1, "Eggplant", 1), 125, "Foraging (5 hours)", "Foraging level 3", 180),
    DISH_O_THE_SEA("Dish O' the Sea", Map.of("Sardine", 2, "Hash Browns", 1), 150, "Fishing (5 hours)", "Fishing level 2", 220),
    SEAFOAM_PUDDING("Seaform Pudding", Map.of("Flounder", 1, "Midnight Carp", 1), 175, "Fishing (10 hours)", "Fishing level 3", 300),
    MINERS_TREAT("Miner's Treat", Map.of("Carrot", 2, "Sugar", 1, "Milk", 1), 125, "Mining (5 hours)", "Mining level 1", 200);

    private final String name;
    private final Map<String, Integer> ingredients;
    private final int energy;
    private final String buff;
    private final String source;
    private final int sellPrice;

    Recipe(String name, Map<String, Integer> ingredients, int energy, String buff, String source, int sellPrice) {
        this.name = name;
        this.ingredients = new HashMap<>(ingredients);
        this.energy = energy;
        this.buff = buff;
        this.source = source;
        this.sellPrice = sellPrice;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Map<String, Integer> getIngredients() {
        return new HashMap<>(ingredients);
    }

    public int getEnergy() {
        return energy;
    }

    public String getBuff() {
        return buff;
    }

    public String getSource() {
        return source;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    @Override
    public String toString() {
        String res = this.name + '\n' + " ingredients : " + '\n';
        for (String s : ingredients.keySet()) {
            res += s + " : " + ingredients.get(s) + '\n';
        }
        return res;
    }

    public static Recipe getRecipe(String name){
        for (Recipe value : Recipe.values()) {
            if(value.name.equalsIgnoreCase(name))
                return value;
        }
        return null;
    }
}