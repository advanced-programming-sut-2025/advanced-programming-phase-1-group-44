package model.enums;

public enum Recipe {
    friedEgg("Fried egg", "1 egg", 50, "", "Starter", 35),
    bakedFish("Baked Fish", "1 Sardine + 1 Salmon + 1 wheat", 75, "", "Starter", 100),
    salad("Salad", "1 leek + 1 dandelion", 113, "", "Starter", 110),
    omelet("Omelet", "1 egg + 1 milk", 100, "", "Stardrop Saloon", 125),
    pumpkinPie("Pumpkin pie", "1 pumpkin + 1 wheat flour + 1 milk + 1 sugar", 225, "", "Stardrop Saloon", 385),
    spaghetti("Spaghetti", "1 wheat flour + 1 tomato", 75, "", "Stardrop Saloon", 120),
    pizza("Pizza", "1 wheat flour + 1 tomato + 1 cheese", 150, "", "Stardrop Saloon", 300),
    tortilla("Tortilla", "1 corn", 50, "", "Stardrop Saloon", 50),
    makiRoll("Maki Roll", "1 any fish + 1 rice + 1 fiber", 100, "", "Stardrop Saloon", 220),
    tripleShotEspresso("Triple Shot Espresso", "3 coffee", 200, "Max Energy + 100 (5 hours)", "Stardrop Saloon", 450),
    cookie("Cookie", "1 wheat flour + 1 sugar + 1 egg", 90, "", "Stardrop Saloon", 140),
    hashBrowns("Hash browns", "1 potato + 1 oil", 90, "Farming (5 hours)", "Stardrop Saloon", 120),
    pancakes("Pancakes", "1 wheat flour + 1 egg", 90, "Foraging (11 hours)", "Stardrop Saloon", 80),
    fruitSalad("Fruit salad", "1 blueberry + 1 melon + 1 apricot", 263, "", "Stardrop Saloon", 450),
    redPlate("Red plate", "1 red cabbage + 1 radish", 240, "Max Energy +50 (3 hours)", "Stardrop Saloon", 400),
    bread("Bread", "1 wheat flour", 50, "", "Stardrop Saloon", 60),
    salmonDinner("Salmon dinner", "1 salmon + 1 Amaranth + 1 Kale", 125, "", "Leah reward", 300),
    vegetableMedley("Vegetable medley", "1 tomato + 1 beet", 165, "", "Foraging Level 2", 120),
    farmersLunch("Farmer's lunch", "1 omelet + 1 parsnip", 200, "Farming (5 hours)", "Farming level 1", 150),
    survivalBurger("Survival burger", "1 bread + 1 carrot + 1 eggplant", 125, "Foraging (5 hours)", "Foraging level 3", 180),
    dishOTheSea("Dish O' the Sea", "2 sardines + 1 hash browns", 150, "Fishing (5 hours)", "Fishing level 2", 220),
    seafoamPudding("Seaform Pudding", "1 Flounder + 1 midnight carp", 175, "Fishing (10 hours)", "Fishing level 3", 300),
    minersTreat("Miner's treat", "2 carrot + 1 sugar + 1 milk", 125, "Mining (5 hours)", "Mining level 1", 200);

    private final String name;
    private final String ingredients;
    private final int energy;
    private final String buff;
    private final String source;
    private final int sellPrice;

    Recipe(String name, String ingredients, int energy, String buff, String source, int sellPrice) {
        this.name = name;
        this.ingredients = ingredients;
        this.energy = energy;
        this.buff = buff;
        this.source = source;
        this.sellPrice = sellPrice;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
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
        return name;
    }
}