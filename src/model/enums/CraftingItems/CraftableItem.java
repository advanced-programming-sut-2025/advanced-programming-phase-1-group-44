package model.enums.CraftingItems;

import java.util.Map;
import java.util.HashMap;

public enum CraftableItem {
    CHERRY_BOMB("Cherry Bomb", Map.of("Copper Ore", 4, "Coal", 1), "Mining Level 1", 50),
    BOMB("Bomb", Map.of("Iron Ore", 4, "Coal", 1), "Mining Level 2", 50),
    MEGA_BOMB("Mega Bomb", Map.of("Gold Ore", 4, "Coal", 1), "Mining Level 3", 50),
    SPRINKLER("Sprinkler", Map.of("Copper Bar", 1, "Iron Bar", 1), "Farming Level 1", null),
    QUALITY_SPRINKLER("Quality Sprinkler", Map.of("Iron Bar", 1, "Gold Bar", 1), "Farming Level 2", null),
    IRIDIUM_SPRINKLER("Iridium Sprinkler", Map.of("Gold Bar", 1, "Iridium Bar", 1), "Farming Level 3", null),
    CHARCOAL_KILN("Charcoal Kiln", Map.of("Wood", 20, "Copper Bar", 2), "Foraging Level 1", null),
    FURNACE("Furnace", Map.of("Copper Ore", 20, "Stone", 25), null, null),
    SCARECROW("Scarecrow", Map.of("Wood", 50, "Coal", 1, "Fiber", 20), null, null),
    DELUXE_SCARECROW("Deluxe Scarecrow", Map.of("Wood", 50, "Coal", 1, "Fiber", 20, "Iridium Ore", 1), "Farming Level 2", null),
    BEE_HOUSE("Bee House", Map.of("Wood", 40, "Coal", 8, "Iron Bar", 1), "Farming Level 1", null),
    CHEESE_PRESS("Cheese Press", Map.of("Wood", 45, "Stone", 45, "Copper Bar", 1), "Farming Level 2", null),
    KEG("Keg", Map.of("Wood", 30, "Copper Bar", 1, "Iron Bar", 1), "Farming Level 3", null),
    LOOM("Loom", Map.of("Wood", 60, "Fiber", 30), "Farming Level 3", null),
    MAYONNAISE_MACHINE("Mayonnaise Machine", Map.of("Wood", 15, "Stone", 15, "Copper Bar", 1), null, null),
    OIL_MAKER("Oil Maker", Map.of("Wood", 100, "Gold Bar", 1, "Iron Bar", 1), "Farming Level 3", null),
    PRESERVES_JAR("Preserves Jar", Map.of("Wood", 50, "Stone", 40, "Coal", 8), "Farming Level 2", null),
    DEHYDRATOR("Dehydrator", Map.of("Wood", 30, "Stone", 20, "Fiber", 30), "Pierre's General Store", null),
    GRASS_STARTER("Grass Starter", Map.of("Wood", 1, "Fiber", 1), "Pierre's General Store", null),
    FISH_SMOKER("Fish Smoker", Map.of("Wood", 50, "Iron Bar", 3, "Coal", 10), "Fish Shop", null),
    MYSTIC_TREE_SEED("Mystic Tree Seed", Map.of("Acorn", 5, "Maple Seed", 5, "Pine Cone", 5, "Mahogany Seed", 5), "Foraging Level 4", 100);

    private final String name;
    private final Map<String, Integer> ingredients;
    private final String source;
    private final Integer sellPrice;

    CraftableItem(String name, Map<String, Integer> ingredients, String source, Integer sellPrice) {
        this.name = name;
        this.ingredients = ingredients;
        this.source = source;
        this.sellPrice = sellPrice;
    }

    public String getName() { return name; }
    public Map<String, Integer> getIngredients() { return new HashMap<>(ingredients); }
    public String getSource() { return source; }
    public Integer getSellPrice() { return sellPrice; }
    public static CraftableItem getCraftableItem(String name){
        for (CraftableItem value : CraftableItem.values()) {
            if(value.name.equalsIgnoreCase(name)){
                return value;
            }
        }
        return null;
    }
}