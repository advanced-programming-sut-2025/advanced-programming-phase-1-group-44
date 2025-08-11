package model.enums;

import java.util.ArrayList;
import java.util.List;

public enum Crop {
    // Spring Crops
    BLUE_JAZZ("Blue Jazz", "Jazz Seeds", "1-2-2-2", 7, true, -1, 50, true, 45, 20, new boolean[]{true, false, false, false}, false),
    CARROT("Carrot", "Carrot Seeds", "1-1-1", 3, true, -1, 35, true, 75, 33, new boolean[]{true, false, false, false}, false),
    CAULIFLOWER("Cauliflower", "Cauliflower Seeds", "1-2-4-4-1", 12, true, -1, 175, true, 75, 33, new boolean[]{true, false, false, false}, true),
    COFFEE_BEAN("Coffee Bean", "Coffee Bean", "1-2-2-3-2", 10, false, 2, 15, false, -1, -1, new boolean[]{true, true, false, false}, false),
    GARLIC("Garlic", "Garlic Seeds", "1-1-1-1", 4, true, -1, 60, true, 20, 9, new boolean[]{true, false, false, false}, false),
    GREEN_BEAN("Green Bean", "Bean Starter", "1-1-1-3-4", 10, false, 3, 40, true, 25, 11, new boolean[]{true, false, false, false}, false),
    KALE("Kale", "Kale Seeds", "1-2-2-1", 6, true, -1, 110, true, 50, 22, new boolean[]{true, false, false, false}, false),
    PARSNIP("Parsnip", "Parsnip Seeds", "1-1-1-1", 4, true, -1, 35, true, 25, 11, new boolean[]{true, false, false, false}, false),
    POTATO("Potato", "Potato Seeds", "1-1-1-2-1", 6, true, -1, 80, true, 25, 11, new boolean[]{true, false, false, false}, false),
    RHUBARB("Rhubarb", "Rhubarb Seeds", "2-2-2-3-4", 13, true, -1, 220, false, -1, -1, new boolean[]{true, false, false, false}, false),
    STRAWBERRY("Strawberry", "Strawberry Seeds", "1-1-2-2-2", 8, false, 4, 120, true, 50, 22, new boolean[]{true, false, false, false}, false),
    TULIP("Tulip", "Tulip Bulb", "1-1-2-2", 6, true, -1, 30, true, 45, 20, new boolean[]{true, false, false, false}, false),
    UNMILLED_RICE("Unmilled Rice", "Rice Shoot", "1-2-2-3", 8, true, -1, 30, true, 3, 1, new boolean[]{true, false, false, false}, false),
    
    // Summer Crops
    BLUEBERRY("Blueberry", "Blueberry Seeds", "1-3-3-4-2", 13, false, 4, 50, true, 25, 11, new boolean[]{false, true, false, false}, false),
    CORN("Corn", "Corn Seeds", "2-3-3-3-3", 14, false, 4, 50, true, 25, 11, new boolean[]{false, true, true, false}, false),
    HOPS("Hops", "Hops Starter", "1-1-2-3-4", 11, false, 1, 25, true, 45, 20, new boolean[]{false, true, false, false}, false),
    HOT_PEPPER("Hot Pepper", "Pepper Seeds", "1-1-1-1-1", 5, false, 3, 40, true, 13, 5, new boolean[]{false, true, false, false}, false),
    MELON("Melon", "Melon Seeds", "1-2-3-3-3", 12, true, -1, 250, true, 113, 50, new boolean[]{false, true, false, false}, true),
    POPPY("Poppy", "Poppy Seeds", "1-2-2-2", 7, true, -1, 140, true, 45, 20, new boolean[]{false, true, false, false}, false),
    RADISH("Radish", "Radish Seeds", "2-1-2-1", 6, true, -1, 90, true, 45, 20, new boolean[]{false, true, false, false}, false),
    RED_CABBAGE("Red Cabbage", "Red Cabbage Seeds", "2-1-2-2-2", 9, true, -1, 260, true, 75, 33, new boolean[]{false, true, false, false}, false),
    STARFRUIT("Starfruit", "Starfruit Seeds", "2-3-2-3-3", 13, true, -1, 750, true, 125, 56, new boolean[]{false, true, false, false}, false),
    SUMMER_SPANGLE("Summer Spangle", "Spangle Seeds", "1-2-3-1", 8, true, -1, 90, true, 45, 20, new boolean[]{false, true, false, false}, false),
    SUMMER_SQUASH("Summer Squash", "Summer Squash Seeds", "1-1-1-2-1", 6, false, 3, 45, true, 63, 28, new boolean[]{false, true, false, false}, false),
    SUNFLOWER("Sunflower", "Sunflower Seeds", "1-2-3-2", 8, true, -1, 80, true, 45, 20, new boolean[]{false, true, true, false}, false),
    TOMATO("Tomato", "Tomato Seeds", "2-2-2-2-3", 11, false, 4, 60, true, 20, 9, new boolean[]{false, true, false, false}, false),
    WHEAT("Wheat", "Wheat Seeds", "1-1-1-1", 4, true, -1, 25, false, -1, -1, new boolean[]{false, true, true, false}, false),
    
    // Fall Crops
    AMARANTH("Amaranth", "Amaranth Seeds", "1-2-2-2", 7, true, -1, 150, true, 50, 22, new boolean[]{false, false, true, false}, false),
    ARTICHOKE("Artichoke", "Artichoke Seeds", "2-2-1-2-1", 8, true, -1, 160, true, 30, 13, new boolean[]{false, false, true, false}, false),
    BEET("Beet", "Beet Seeds", "1-1-2-2", 6, true, -1, 100, true, 30, 13, new boolean[]{false, false, true, false}, false),
    BOK_CHOY("Bok Choy", "Bok Choy Seeds", "1-1-1-1", 4, true, -1, 80, true, 25, 11, new boolean[]{false, false, true, false}, false),
    BROCCOLI("Broccoli", "Broccoli Seeds", "2-2-2-2", 8, false, 4, 70, true, 63, 28, new boolean[]{false, false, true, false}, false),
    CRANBERRIES("Cranberries", "Cranberry Seeds", "1-2-1-1-2", 7, false, 5, 75, true, 38, 17, new boolean[]{false, false, true, false}, false),
    EGGPLANT("Eggplant", "Eggplant Seeds", "1-1-1-1", 5, false, 5, 60, true, 20, 9, new boolean[]{false, false, true, false}, false),
    FAIRY_ROSE("Fairy Rose", "Fairy Seeds", "1-4-4-3", 12, true, -1, 290, true, 45, 20, new boolean[]{false, false, true, false}, false),
    GRAPE("Grape", "Grape Starter", "1-1-2-3-3", 10, false, 3, 80, true, 38, 17, new boolean[]{false, false, true, false}, false),
    PUMPKIN("Pumpkin", "Pumpkin Seeds", "1-2-3-4-3", 13, true, -1, 320, false, -1, -1, new boolean[]{false, false, true, false}, true),
    YAM("Yam", "Yam Seeds", "1-3-3-3", 10, true, -1, 160, true, 45, 20, new boolean[]{false, false, true, false}, false),
    SWEET_GEM_BERRY("Sweet Gem Berry", "Rare Seed", "2-4-6-6-6", 24, true, -1, 3000, false, -1, -1, new boolean[]{false, false, true, false}, false),
    
    // Winter Crop
    POWDERMELON("Powdermelon", "Powdermelon Seeds", "1-2-1-2-1", 7, true, -1, 60, true, 63, 28, new boolean[]{false, false, false, true}, true),
    
    // Special (Multi-season)
    ANCIENT_FRUIT("Ancient Fruit", "Ancient Seeds", "2-7-7-7-5", 28, false, 7, 550, false, -1, -1, new boolean[]{true, true, true, false}, false);

    // Enum fields
    private final String name;
    private final String source;
    private final String stages;
    private final int totalHarvestTime;
    private final boolean oneTime;
    private final int regrowthTime;      // -1 if no regrowth
    private final int baseSellPrice;
    private final boolean isEdible;
    private final int energy;            // -1 if not edible
    private final int baseHealth;        // -1 if not edible
    private final boolean[] seasons;     // [Spring, Summer, Fall, Winter]
    private final boolean canBecomeGiant;
    
    // Constructor
    private Crop(String name, String source, String stages, int totalHarvestTime, boolean oneTime,
                int regrowthTime, int baseSellPrice, boolean isEdible, int energy, int baseHealth,
                boolean[] seasons, boolean canBecomeGiant) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.oneTime = oneTime;
        this.regrowthTime = regrowthTime;
        this.baseSellPrice = baseSellPrice;
        this.isEdible = isEdible;
        this.energy = energy;
        this.baseHealth = baseHealth;
        this.seasons = seasons;
        this.canBecomeGiant = canBecomeGiant;
    }
    
    // Getters
    public String getName() { return name; }
    public String getSource() { return source; }
    public String getStages() { return stages; }
    public int getTotalHarvestTime() { return totalHarvestTime; }
    public boolean isOneTime() { return oneTime; }
    public int getRegrowthTime() { return regrowthTime; }
    public int getBaseSellPrice() { return baseSellPrice; }
    public boolean isEdible() { return isEdible; }
    public int getEnergy() { return energy; }
    public int getBaseHealth() { return baseHealth; }
    public boolean[] getSeasons() { return seasons; }
    public boolean canBecomeGiant() { return canBecomeGiant; }

    // Helper methods
    public boolean growsInSpring() { return seasons[0]; }
    public boolean growsInSummer() { return seasons[1]; }
    public boolean growsInFall() { return seasons[2]; }
    public boolean growsInWinter() { return seasons[3]; }
    
    public boolean isMultiSeason() {
        int count = 0;
        for (boolean season : seasons) {
            if (season) count++;
        }
        return count > 1;
    }
    
    public String getSeasonString() {
        List<String> activeSeasons = new ArrayList<>();
        if (seasons[0]) activeSeasons.add("Spring");
        if (seasons[1]) activeSeasons.add("Summer");
        if (seasons[2]) activeSeasons.add("Fall");
        if (seasons[3]) activeSeasons.add("Winter");
        
        return String.join(" & ", activeSeasons);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Source: ").append(source).append("\n");
        sb.append("Stages: ").append(stages).append("\n");
        sb.append("Total Harvest Time: ").append(totalHarvestTime).append("\n");
        sb.append("One Time: ").append(oneTime ? "TRUE" : "FALSE").append("\n");
        sb.append("Regrowth Time: ").append(regrowthTime == -1 ? "" : regrowthTime).append("\n");
        sb.append("Base Sell Price: ").append(baseSellPrice).append("\n");
        sb.append("Is Edible: ").append(isEdible ? "TRUE" : "FALSE").append("\n");
        sb.append("Base Energy: ").append(energy).append("\n");
        sb.append("Base Health: ").append(baseHealth).append("\n");
        
        // Build season string
        StringBuilder seasonBuilder = new StringBuilder();
        if (seasons[0]) seasonBuilder.append("Spring");
        if (seasons[1]) seasonBuilder.append(seasonBuilder.length() > 0 ? ", Summer" : "Summer");
        if (seasons[2]) seasonBuilder.append(seasonBuilder.length() > 0 ? ", Fall" : "Fall");
        if (seasons[3]) seasonBuilder.append(seasonBuilder.length() > 0 ? ", Winter" : "Winter");
        sb.append("Season: ").append(seasonBuilder.toString()).append("\n");
        
        sb.append("Can Become Giant: ").append(canBecomeGiant ? "TRUE" : "FALSE");
        
        return sb.toString();
    
    }
}