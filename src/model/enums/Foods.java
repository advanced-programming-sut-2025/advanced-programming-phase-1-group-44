package model.enums;

public enum Foods {
    FriedEgg(50, 35),
    BakedFish(75, 100),
    Salad(113, 110),
    Omelet(100, 125),  // Fixed spelling of "Olmelet" to "Omelet"
    PumpkinPie(225, 385),
    Spaghetti(75, 120),
    Pizza(150, 300),
    Tortilla(50, 50),
    MakiRoll(100, 220),
    TripleShotEspresso(200, 450),
    Cookie(90, 140),
    HashBrowns(90, 120),
    Pancakes(90, 80),
    FruitSalad(263, 450),
    RedPlate(240, 400),
    Bread(50, 60),
    SalmonDinner(125, 300),
    VegetableMedley(165, 120),
    FarmersLunch(200, 150),  // Fixed "farmer's lunch" to "FarmersLunch" (no apostrophe)
    SurvivalBurger(125, 180),
    DishOTheSea(150, 220),  // "Dish O' the Sea" -> "DishOTheSea"
    SeafoamPudding(175, 300),  // Fixed "seaform Pudding" to "SeafoamPudding"
    MinersTreat(125, 200);  // "miner's treat" -> "MinersTreat"

    private final int energy, sellPrice;
    Foods(int energy, int sellPrice) {
        this.energy = energy;
        this.sellPrice = sellPrice;
    }

    public int getEnergy() {
        return energy;
    }
    public int getSellPrice() {
        return sellPrice;
    }

}
