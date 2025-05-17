package model.enums;

import java.util.ArrayList;

public enum Crops {
    BLUE_JAZZ(
            "Blue Jazz", "Jazz Seeds", "1-2-2-2",
            7, true, null, 50, true, 45, 20, "Spring", false
    ),
    CARROT(
            "Carrot", "Carrot Seeds", "1-1-1",
            3, true, null, 35, true, 75, 33, "Spring", false
    ),
    CAULIFLOWER(
            "Cauliflower", "Cauliflower Seeds", "1-2-4-4-1",
            12, true, null, 175, true, 75, 33, "Spring", true
    ),
    COFFEE_BEAN(
            "Coffee Bean", "Coffee Bean", "1-2-2-3-2",
            10, false, 2, 15, false, null, null, "Spring & Summer", false
    ),
    GARLIC(
            "Garlic", "Garlic Seeds", "1-1-1-1",
            4, true, null, 60, true, 20, 9, "Spring", false
    ),
    GREEN_BEAN(
            "Green Bean", "Bean Starter", "1-1-1-3-4",
            10, false, 3, 40, true, 25, 11, "Spring", false
    ),
    KALE(
            "Kale", "Kale Seeds", "1-2-2-1",
            6, true, null, 110, true, 50, 22, "Spring", false
    ),
    PARSNIP(
            "Parsnip", "Parsnip Seeds", "1-1-1-1",
            4, true, null, 35, true, 25, 11, "Spring", false
    ),
    POTATO(
            "Potato", "Potato Seeds", "1-1-1-2-1",
            6, true, null, 80, true, 25, 11, "Spring", false
    ),
    RHUBARB(
            "Rhubarb", "Rhubarb Seeds", "2-2-2-3-4",
            13, true, null, 220, false, null, null, "Spring", false
    ),
    STRAWBERRY(
            "Strawberry", "Strawberry Seeds", "1-1-2-2-2",
            8, false, 4, 120, true, 50, 22, "Spring", false
    ),
    TULIP(
            "Tulip", "Tulip Bulb", "1-1-2-2",
            6, true, null, 30, true, 45, 20, "Spring", false
    ),
    UNMILLED_RICE(
            "Unmilled Rice", "Rice Shoot", "1-2-2-3",
            8, true, null, 30, true, 3, 1, "Spring", false
    ),
    BLUEBERRY(
            "Blueberry", "Blueberry Seeds", "1-3-3-4-2",
            13, false, 4, 50, true, 25, 11, "Summer", false
    ),
    CORN(
            "Corn", "Corn Seeds", "2-3-3-3-3",
            14, false, 4, 50, true, 25, 11, "Summer & Fall", false
    ),
    HOPS(
            "Hops", "Hops Starter", "1-1-2-3-4",
            11, false, 1, 25, true, 45, 20, "Summer", false
    ),
    HOT_PEPPER(
            "Hot Pepper", "Pepper Seeds", "1-1-1-1-1",
            5, false, 3, 40, true, 13, 5, "Summer", false
    ),
    MELON(
            "Melon", "Melon Seeds", "1-2-3-3-3",
            12, true, null, 250, true, 113, 50, "Summer", true
    ),
    POPPY(
            "Poppy", "Poppy Seeds", "1-2-2-2",
            7, true, null, 140, true, 45, 20, "Summer", false
    ),
    RADISH(
            "Radish", "Radish Seeds", "2-1-2-1",
            6, true, null, 90, true, 45, 20, "Summer", false
    ),
    RED_CABBAGE(
            "Red Cabbage", "Red Cabbage Seeds", "2-1-2-2-2",
            9, true, null, 260, true, 75, 33, "Summer", false
    ),
    STARFRUIT(
            "Starfruit", "Starfruit Seeds", "2-3-2-3-3",
            13, true, null, 750, true, 125, 56, "Summer", false
    ),
    SUMMER_SPANGLE(
            "Summer Spangle", "Spangle Seeds", "1-2-3-1",
            8, true, null, 90, true, 45, 20, "Summer", false
    ),
    SUMMER_SQUASH(
            "Summer Squash", "Summer Squash Seeds", "1-1-1-2-1",
            6, false, 3, 45, true, 63, 28, "Summer", false
    ),
    SUNFLOWER(
            "Sunflower", "Sunflower Seeds", "1-2-3-2",
            8, true, null, 80, true, 45, 20, "Summer & Fall", false
    ),
    TOMATO(
            "Tomato", "Tomato Seeds", "2-2-2-2-3",
            11, false, 4, 60, true, 20, 9, "Summer", false
    ),
    WHEAT(
            "Wheat", "Wheat Seeds", "1-1-1-1",
            4, true, null, 25, false, null, null, "Summer & Fall", false
    ),
    AMARANTH(
            "Amaranth", "Amaranth Seeds", "1-2-2-2",
            7, true, null, 150, true, 50, 22, "Fall", false
    ),
    ARTICHOKE(
            "Artichoke", "Artichoke Seeds", "2-2-1-2-1",
            8, true, null, 160, true, 30, 13, "Fall", false
    ),
    BEET(
            "Beet", "Beet Seeds", "1-1-2-2",
            6, true, null, 100, true, 30, 13, "Fall", false
    ),
    BOK_CHOY(
            "Bok Choy", "Bok Choy Seeds", "1-1-1-1",
            4, true, null, 80, true, 25, 11, "Fall", false
    ),
    BROCCOLI(
            "Broccoli", "Broccoli Seeds", "2-2-2-2",
            8, false, 4, 70, true, 63, 28, "Fall", false
    ),
    CRANBERRIES(
            "Cranberries", "Cranberry Seeds", "1-2-1-1-2",
            7, false, 5, 75, true, 38, 17, "Fall", false
    ),
    EGGPLANT(
            "Eggplant", "Eggplant Seeds", "1-1-1-1",
            5, false, 5, 60, true, 20, 9, "Fall", false
    ),
    FAIRY_ROSE(
            "Fairy Rose", "Fairy Seeds", "1-4-4-3",
            12, true, null, 290, true, 45, 20, "Fall", false
    ),
    GRAPE(
            "Grape", "Grape Starter", "1-1-2-3-3",
            10, false, 3, 80, true, 38, 17, "Fall", false
    ),
    PUMPKIN(
            "Pumpkin", "Pumpkin Seeds", "1-2-3-4-3",
            13, true, null, 320, false, null, null, "Fall", true
    ),
    YAM(
            "Yam", "Yam Seeds", "1-3-3-3",
            10, true, null, 160, true, 45, 20, "Fall", false
    ),
    SWEET_GEM_BERRY(
            "Sweet Gem Berry", "Rare Seed", "2-4-6-6-6",
            24, true, null, 3000, false, null, null, "Fall", false
    ),
    POWDERMELON(
            "Powdermelon", "Powdermelon Seeds", "1-2-1-2-1",
            7, true, null, 60, true, 63, 28, "Winter", true
    ),
    ANCIENT_FRUIT(
            "Ancient Fruit", "Ancient Seeds", "2-7-7-7-5",
            28, false, 7, 550, false, null, null, "All Except Winter", false
    );

    private final String name;
    private final String source;
    private ArrayList<Integer> Stages=new ArrayList<Integer>();
    private final int totalHarvestTime;
    private final boolean oneTime;
    private final Integer regrowthTime;
    private final int baseSellPrice;
    private final boolean edible;
    private final Integer energy;
    private final Integer baseHealth;
    private final String season;
    private final boolean canBecomeGiant;

    Crops(String name, String source, String stages,
          int totalHarvestTime, boolean oneTime, Integer regrowthTime,
          int baseSellPrice, boolean edible, Integer energy,
          Integer baseHealth, String season, boolean canBecomeGiant) {

        this.name = name;
        this.source = source;
        for(int i=0;i<stages.length();i+=2) {
            Stages.add(Integer.parseInt("" + stages.charAt(i)));
        }
        this.totalHarvestTime = totalHarvestTime;
        this.oneTime = oneTime;
        this.regrowthTime = regrowthTime;
        this.baseSellPrice = baseSellPrice;
        this.edible = edible;
        this.energy = energy;
        this.baseHealth = baseHealth;
        this.season = season;
        this.canBecomeGiant = canBecomeGiant;
    }

    public String getName() { return name; }
    public String getSource() { return source; }
    public ArrayList<Integer> getStages() { return Stages; }
    public int getTotalHarvestTime() { return totalHarvestTime; }
    public boolean isOneTime() { return oneTime; }
    public Integer getRegrowthTime() { return regrowthTime; }
    public int getBaseSellPrice() { return baseSellPrice; }
    public boolean isEdible() { return edible; }
    public Integer getEnergy() { return energy; }
    public Integer getBaseHealth() { return baseHealth; }
    public String getSeason() { return season; }
    public boolean canBecomeGiant() { return canBecomeGiant; }}