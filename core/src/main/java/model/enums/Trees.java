package model.enums;

import java.util.ArrayList;

public enum Trees {
    APRICOT_TREE(
            "Apricot Tree", "Apricot Sapling", "7-7-7-7",
            28, "Apricot", 1, 59, true, 38, 17, "Spring"
    ),
    CHERRY_TREE(
            "Cherry Tree", "Cherry Sapling", "7-7-7-7",
            28, "Cherry", 1, 80, true, 38, 17, "Spring"
    ),
    BANANA_TREE(
            "Banana Tree", "Banana Sapling", "7-7-7-7",
            28, "Banana", 1, 150, true, 75, 33, "Summer"
    ),
    MANGO_TREE(
            "Mango Tree", "Mango Sapling", "7-7-7-7",
            28, "Mango", 1, 130, true, 100, 45, "Summer"
    ),
    ORANGE_TREE(
            "Orange Tree", "Orange Sapling", "7-7-7-7",
            28, "Orange", 1, 100, true, 38, 17, "Summer"
    ),
    PEACH_TREE(
            "Peach Tree", "Peach Sapling", "7-7-7-7",
            28, "Peach", 1, 140, true, 38, 17, "Summer"
    ),
    APPLE_TREE(
            "Apple Tree", "Apple Sapling", "7-7-7-7",
            28, "Apple", 1, 100, true, 38, 17, "Fall"
    ),
    POMEGRANATE_TREE(
            "Pomegranate Tree", "Pomegranate Sapling", "7-7-7-7",
            28, "Pomegranate", 1, 140, true, 38, 17, "Fall"
    ),
    OAK_TREE(
            "Oak Tree", "Acorns", "7-7-7-7",
            28, "Oak Resin", 7, 150, false, null, null, "Special"
    ),
    MAPLE_TREE(
            "Maple Tree", "Maple Seeds", "7-7-7-7",
            28, "Maple Syrup", 9, 200, false, null, null, "Special"
    ),
    PINE_TREE(
            "Pine Tree", "Pine Cones", "7-7-7-7",
            28, "Pine Tar", 5, 100, false, null, null, "Special"
    ),
    MAHOGANY_TREE(
            "Mahogany Tree", "Mahogany Seeds", "7-7-7-7",
            28, "Sap", 1, 2, true, -2, 0, "Special"
    ),
    MUSHROOM_TREE(
            "Mushroom Tree", "Mushroom Tree Seeds", "7-7-7-7",
            28, "Common Mushroom", 1, 40, true, 38, 17, "Special"
    ),
    MYSTIC_TREE(
            "Mystic Tree", "Mystic Tree Seeds", "7-7-7-7",
            28, "Mystic Syrup", 7, 1000, true, 500, 225, "Special"
    );

    private final String name;
    private final String source;
    private ArrayList<Integer> Stages=new ArrayList<Integer>();
    private final int totalHarvestTime;
    private final String fruit;
    private final int fruitHarvestCycle;
    private final int fruitBaseSellPrice;
    private final boolean fruitEdible;
    private final Integer fruitEnergy;
    private final Integer fruitBaseHealth;
    private final String season;

    Trees(String name, String source, String stages,
         int totalHarvestTime, String fruit, int fruitHarvestCycle,
         int fruitBaseSellPrice, boolean fruitEdible, Integer fruitEnergy,
         Integer fruitBaseHealth, String season) {

        this.name = name;
        this.source = source;
        for(int i=0;i<stages.length();i+=2) {
            Stages.add(Integer.parseInt("" + stages.charAt(i)));
        }        this.totalHarvestTime = totalHarvestTime;
        this.fruit = fruit;
        this.fruitHarvestCycle = fruitHarvestCycle;
        this.fruitBaseSellPrice = fruitBaseSellPrice;
        this.fruitEdible = fruitEdible;
        this.fruitEnergy = fruitEnergy;
        this.fruitBaseHealth = fruitBaseHealth;
        this.season = season;
    }

    public String getName() { return name; }
    public String getSource() { return source; }
    public ArrayList<Integer> getStages() { return Stages; }
    public int getTotalHarvestTime() { return totalHarvestTime; }
    public String getFruit() { return fruit; }
    public int getFruitHarvestCycle() { return fruitHarvestCycle; }
    public int getFruitBaseSellPrice() { return fruitBaseSellPrice; }
    public boolean isFruitEdible() { return fruitEdible; }
    public Integer getFruitEnergy() { return fruitEnergy; }
    public Integer getFruitBaseHealth() { return fruitBaseHealth; }
    public String getSeason() { return season; }
}