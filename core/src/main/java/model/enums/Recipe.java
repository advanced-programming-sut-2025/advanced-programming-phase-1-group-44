package model.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public enum Recipe {
    FRIED_EGG("Fried Egg", Map.of("Egg", 1), 50, "Check", 10, "Starter", 35, "Recipe/Fried_Egg.png"),
    BAKED_FISH("Baked Fish", Map.of("Sardine", 1, "Salmon", 1, "Wheat", 1), 75, "", "Starter", 100, "Recipe/Baked_Fish.png"),
    SALAD("Salad", Map.of("Leek", 1, "Dandelion", 1), 113, "", "Starter", 110 , "Recipe/Salad.png"),
    OMELET("Omelet", Map.of("Egg", 1, "Milk", 1), 100, "", "Stardrop Saloon", 125, "Recipe/Omelet.png"),
    PUMPKIN_PIE("Pumpkin Pie", Map.of("Pumpkin", 1, "Wheat Flour", 1, "Milk", 1, "Sugar", 1), 225, "", "Stardrop Saloon", 385, "Recipe/Pumpkin_Pie.png"),
    SPAGHETTI("Spaghetti", Map.of("Wheat Flour", 1, "Tomato", 1), 75, "", "Stardrop Saloon", 120 , "Recipe/Spaghetti.png"),
    PIZZA("Pizza", Map.of("Wheat Flour", 1, "Tomato", 1, "Cheese", 1), 150, "", "Stardrop Saloon", 300, "Recipe/Pizza.png"),
    TORTILLA("Tortilla", Map.of("Corn", 1), 50, "", "Stardrop Saloon", 50, "Recipe/Tortilla.png"),
    MAKI_ROLL("Maki Roll", Map.of("Any Fish", 1, "Rice", 1, "Fiber", 1), 100, "", "Stardrop Saloon", 220 , "Recipe/Maki_Roll.png"),
    TRIPLE_SHOT_ESPRESSO("Triple Shot Espresso", Map.of("Coffee", 3), 200, "100",5, "Stardrop Saloon", 450 , "Recipe/Triple_Shot_Espresso.png"),
    COOKIE("Cookie", Map.of("Wheat Flour", 1, "Sugar", 1, "Egg", 1), 90, "", "Stardrop Saloon", 140 , "Recipe/Cookie.png"),
    HASH_BROWNS("Hash Browns", Map.of("Potato", 1, "Oil", 1), 90, "Farming",5, "Stardrop Saloon", 120, "Recipe/Hashbrowns.png"),
    PANCAKES("Pancakes", Map.of("Wheat Flour", 1, "Egg", 1), 90, "Foraging", 11, "Stardrop Saloon", 80, "Recipe/Pancakes.png"),
    FRUIT_SALAD("Fruit Salad", Map.of("Blueberry", 1, "Melon", 1, "Apricot", 1), 263, "", "Stardrop Saloon", 450 , "Recipe/Fruit_Salad.png"),
    RED_PLATE("Red Plate", Map.of("Red Cabbage", 1, "Radish", 1), 240, "50",3, "Stardrop Saloon", 400 , "Recipe/Red_Plate.png"),
    BREAD("Bread", Map.of("Wheat Flour", 1), 50, "", "Stardrop Saloon", 60 , "Recipe/Bread.png"),
    SALMON_DINNER("Salmon Dinner", Map.of("Salmon", 1, "Amaranth", 1, "Kale", 1), 125, "", "Leah reward", 300 , "Recipe/Salmon_Dinner.png"),
    VEGETABLE_MEDLEY("Vegetable Medley", Map.of("Tomato", 1, "Beet", 1), 165, "", "Foraging Level 2", 120 , "Recipe/Vegetable_Medley.png"),
    FARMERS_LUNCH("Farmer's Lunch", Map.of("Omelet", 1, "Parsnip", 1), 200, "Farming", 5, "Farming level 1", 150, "Recipe/Farmer%27s_Lunch.png"),
    SURVIVAL_BURGER("Survival Burger", Map.of("Bread", 1, "Carrot", 1, "Eggplant", 1), 125, "Foraging (5 hours)", "Foraging level 3", 180, "Recipe/Survival_Burger.png"),
    DISH_O_THE_SEA("Dish O' the Sea", Map.of("Sardine", 2, "Hash Browns", 1), 150, "Fishing", 5, "Fishing level 2", 220, "Recipe/Dish_O%27_The_Sea.png"),
    SEAFOAM_PUDDING("Seaform Pudding", Map.of("Flounder", 1, "Midnight Carp", 1), 175, "Fishing", 10, "Fishing level 3", 300, "Recipe/Seafoam_Pudding.png"),
    MINERS_TREAT("Miner's Treat", Map.of("Carrot", 2, "Sugar", 1, "Milk", 1), 125, "Mining", 5, "Mining level 1", 200, "Recipe/Miner%27s_Treat.png");

    private final String name;
    private final Map<String, Integer> ingredients;
    private final int energy;
    private final String buff;
    private final int buffTime;
    private final String source;
    private final int sellPrice;
    private final String imagePath;

    Recipe(String name, Map<String, Integer> ingredients, int energy, String buff, String source, int sellPrice) {
        this.name = name;
        this.ingredients = new HashMap<>(ingredients);
        this.energy = energy;
        this.buff = buff;
        this.source = source;
        this.sellPrice = sellPrice;
        this.buffTime = 0;
        this.imagePath = "";
    }
    Recipe(String name, Map<String, Integer> ingredients, int energy, String buff, String source, int sellPrice, String imagePath) {
        this.name = name;
        this.ingredients = new HashMap<>(ingredients);
        this.energy = energy;
        this.buff = buff;
        this.source = source;
        this.sellPrice = sellPrice;
        this.buffTime = 0;
        this.imagePath = imagePath;
    }
    Recipe(String name, Map<String, Integer> ingredients, int energy, String buff,int buffTime, String source, int sellPrice) {
        this.name = name;
        this.ingredients = new HashMap<>(ingredients);
        this.energy = energy;
        this.buff = buff;
        this.source = source;
        this.sellPrice = sellPrice;
        this.buffTime = buffTime;
        this.imagePath = "";
    }

    Recipe(String name, Map<String, Integer> ingredients, int energy, String buff, int buffTime, String source, int sellPrice, String imagePath) {
        this.name = name;
        this.ingredients = ingredients;
        this.energy = energy;
        this.buff = buff;
        this.buffTime = buffTime;
        this.source = source;
        this.sellPrice = sellPrice;
        this.imagePath = imagePath;
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

    public int getBuffTime() {
        return buffTime;
    }

    public String getImagePath(){return imagePath;}

    public boolean isLocked(){return false;}

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

    public static List<Recipe> getItems(){
        List<Recipe> items = new ArrayList<>();
        for (Recipe value : values()) {
            items.add(value);
        }
        return items;
    }
}
