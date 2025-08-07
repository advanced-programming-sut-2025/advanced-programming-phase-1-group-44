package model.enums.CraftingItems;

import java.util.*;

public enum CraftableItem {
    CHERRY_BOMB("Cherry Bomb", Map.of("Copper Ore", 4, "Coal", 1), "Mining Level 1", 50, "Crafting/Cherry_Bomb.png"),
    BOMB("Bomb", Map.of("Iron Ore", 4, "Coal", 1), "Mining Level 2", 50, "Crafting/Bomb.png"),
    MEGA_BOMB("Mega Bomb", Map.of("Gold Ore", 4, "Coal", 1), "Mining Level 3", 50, "Crafting/Mega_Bomb.png"),
    SPRINKLER("Sprinkler", Map.of("Copper Bar", 1, "Iron Bar", 1), "Farming Level 1", 0, "Crafting/Sprinkler.png"),
    QUALITY_SPRINKLER("Quality Sprinkler", Map.of("Iron Bar", 1, "Gold Bar", 1), "Farming Level 2", 0, "Crafting/Quality_Sprinkler.png"),
    IRIDIUM_SPRINKLER("Iridium Sprinkler", Map.of("Gold Bar", 1, "Iridium Bar", 1), "Farming Level 3", 0, "Crafting/Iridium_Sprinkler.png"),
    CHARCOAL_KILN("Charcoal Kiln", Map.of("Wood", 20, "Copper Bar", 2), "Foraging Level 1", 0, "Crafting/Charcoal_Kiln.png"),
    FURNACE("Furnace", Map.of("Copper Ore", 20, "Stone", 25), null, 0, "Crafting/Furnace.png"),
    SCARECROW("Scarecrow", Map.of("Wood", 50, "Coal", 1, "Fiber", 20), null, 0, "Crafting/Scarecrow.png"),
    DELUXE_SCARECROW("Deluxe Scarecrow", Map.of("Wood", 50, "Coal", 1, "Fiber", 20, "Iridium Ore", 1), "Farming Level 2", 0 , "Crafting/Deluxe_Scarecrow.png"),
    BEE_HOUSE("Bee House", Map.of("Wood", 40, "Coal", 8, "Iron Bar", 1), "Farming Level 1", 0, "Crafting/Bee_House.png"),
    CHEESE_PRESS("Cheese Press", Map.of("Wood", 45, "Stone", 45, "Copper Bar", 1), "Farming Level 2", 0, "Crafting/Cheese_Press.png"),
    KEG("Keg", Map.of("Wood", 30, "Copper Bar", 1, "Iron Bar", 1), "Farming Level 3", 0, "Crafting/Keg.png"),
    LOOM("Loom", Map.of("Wood", 60, "Fiber", 30), "Farming Level 3", 0, "Crafting/Loom.png"),
    MAYONNAISE_MACHINE("Mayonnaise Machine", Map.of("Wood", 15, "Stone", 15, "Copper Bar", 1), null, 0, "Crafting/Mayonnaise_Machine.png"),
    OIL_MAKER("Oil Maker", Map.of("Wood", 100, "Gold Bar", 1, "Iron Bar", 1), "Farming Level 3", 0, "Crafting/Oil_Maker.png"),
    PRESERVES_JAR("Preserves Jar", Map.of("Wood", 50, "Stone", 40, "Coal", 8), "Farming Level 2", 0, "Crafting/Preserves_Jar.png"),
    DEHYDRATOR("Dehydrator", Map.of("Wood", 30, "Stone", 20, "Fiber", 30), "Pierre's General Store", 0, "Crafting/Dehydrator.png"),
    GRASS_STARTER("Grass Starter", Map.of("Wood", 1, "Fiber", 1), "Pierre's General Store", 0, "Crafting/Grass_Starter.png"),
    FISH_SMOKER("Fish Smoker", Map.of("Wood", 50, "Iron Bar", 3, "Coal", 10), "Fish Shop", 0, "Crafting/Fish_Smoker.png"),
    MYSTIC_TREE_SEED("Mystic Tree Seed", Map.of("Acorn", 5, "Maple Seed", 5, "Pine Cone", 5, "Mahogany Seed", 5), "Foraging Level 4", 100, "Crafting/Mystic_Tree_Seed.png");

    private final String name;
    private final Map<String, Integer> ingredients;
    private final String source;
    private final Integer sellPrice;
    public final String imagePath;

    CraftableItem(String name, Map<String, Integer> ingredients, String source, Integer sellPrice) {
        this.name = name;
        this.ingredients = ingredients;
        this.source = source;
        this.sellPrice = sellPrice;
        this.imagePath = "";
    }

    CraftableItem(String name, Map<String, Integer> ingredients, String source, Integer sellPrice, String imagePath) {
        this.name = name;
        this.ingredients = ingredients;
        this.source = source;
        this.sellPrice = sellPrice;
        this.imagePath = imagePath;
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

    @Override
    public String toString() {
        String res = this.name + '\n' + "ingredients : " + '\n';
        for (String s : ingredients.keySet()) {
            res += s + " : " + ingredients.get(s) + '\n';
        }
        return res;
    }

    public List<CraftableItem> getItems(){
        ArrayList<CraftableItem> items = new ArrayList<>();
        items.addAll(Arrays.asList(values()));
        return items;
    }

}
