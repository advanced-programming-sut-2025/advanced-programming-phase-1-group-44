package model.enums.StoreItems;

import model.enums.Season;

import java.util.Arrays;
import java.util.List;

public enum ShopItems {
    RICE("Rice", Arrays.asList(200, 200, 200, 200), -1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    WHEAT_FLOUR("Wheat Flour", Arrays.asList(100, 100, 100, 100), -1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    BOUQUET("Bouquet", Arrays.asList(1000, 1000, 1000, 1000), 2, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    WEDDING_RING("Wedding Ring", Arrays.asList(10000, 10000, 10000, 10000), 2, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    DEHYDRATOR_RECIPE("Dehydrator (Recipe)", Arrays.asList(10000, 10000, 10000, 10000), 1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    GRASS_STARTER_RECIPE("Grass Starter (Recipe)", Arrays.asList(1000, 1000, 1000, 1000), 1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    SUGAR("Sugar", Arrays.asList(100, 100, 100, 100), -1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    OIL("Oil", Arrays.asList(200, 200, 200, 200), -1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    VINEGAR("Vinegar", Arrays.asList(200, 200, 200, 200), -1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    DELUXE_RETAINING_SOIL("Deluxe Retaining Soil", Arrays.asList(150, 150, 150, 150), -1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    GRASS_STARTER("Grass Starter", Arrays.asList(100, 100, 100, 100), -1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    SPEED_GRO("Speed-Gro", Arrays.asList(100, 100, 100, 100), -1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    APPLE_SAPLING("Apple Sapling", Arrays.asList(4000, 4000, 4000, 4000), -1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    APRICOT_SAPLING("Apricot Sapling", Arrays.asList(2000, 2000, 2000, 2000), -1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    CHERRY_SAPLING("Cherry Sapling", Arrays.asList(3400, 3400, 3400, 3400), -1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    ORANGE_SAPLING("Orange Sapling", Arrays.asList(4000, 4000, 4000, 4000), -1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    PEACH_SAPLING("Peach Sapling", Arrays.asList(6000, 6000, 6000, 6000), -1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    POMEGRANATE_SAPLING("Pomegranate Sapling", Arrays.asList(6000, 6000, 6000, 6000), -1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    BASIC_RETAINING_SOIL("Basic Retaining Soil", Arrays.asList(100, 100, 100, 100), -1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    QUALITY_RETAINING_SOIL("Quality Retaining Soil", Arrays.asList(150, 150, 150, 150), -1, Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    PARSNIP_SEEDS("Parsnip Seeds", List.of(20, 30, 30, 30), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    BEAN_STARTER("Bean Starter", List.of(60, 90, 90, 90), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", List.of(80, 120, 120, 120), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    POTATO_SEEDS("Potato Seeds", List.of(50, 75, 75, 75), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    TULIP_BULB("Tulip Bulb", List.of(20, 30, 30, 30), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    KALE_SEEDS("Kale Seeds", List.of(70, 105, 105, 105), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    JAZZ_SEEDS("Jazz Seeds", List.of(30, 45, 45, 45), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    GARLIC_SEEDS("Garlic Seeds", List.of(40, 60, 60, 60), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    RICE_SHOOT("Rice Shoot", List.of(40, 60, 60, 60), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    MELON_SEEDS("Melon Seeds", List.of(80, 120, 120, 120), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    TOMATO_SEEDS("Tomato Seeds", List.of(50, 75, 75, 75), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    BLUEBERRY_SEEDS("Blueberry Seeds", List.of(80, 120, 120, 120), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    PEPPER_SEEDS("Pepper Seeds", List.of(40, 60, 60, 60), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    WHEAT_SEEDS("Wheat Seeds", List.of(10, 15, 15, 15), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    RADISH_SEEDS("Radish Seeds", List.of(40, 60, 60, 60), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    POPPY_SEEDS("Poppy Seeds", List.of(100, 150, 150, 150), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    SPANGLE_SEEDS("Spangle Seeds", List.of(50, 75, 75, 75), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    HOPS_STARTER("Hops Starter", List.of(60, 90, 90, 90), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    CORN_SEEDS("Corn Seeds", List.of(150, 225, 225, 225), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    SUNFLOWER_SEEDS("Sunflower Seeds", List.of(200, 300, 300, 300), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    RED_CABBAGE_SEEDS("Red Cabbage Seeds", List.of(100, 150, 150, 150), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    FISH_SMOKER_RECIPE("Fish Smoker (Recipe)", List.of(10000, 10000, 10000, 10000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    TROUT_SOUP("Trout Soup", List.of(250, 250, 250, 250), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    BAMBOO_POLE("Bamboo Pole", List.of(500, 500, 500, 500), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    TRAINING_ROD("Training Rod", List.of(25, 25, 25, 25), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    FIBERGLASS_ROD("Fiberglass Rod", List.of(1800, 1800, 1800, 1800), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 2),
    IRIDIUM_ROD("Iridium Rod", List.of(7500, 7500, 7500, 7500), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 4),
    JOJA_COLA("Joja Cola", List.of(75, 75, 75, 75), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    ANCIENT_SEED("Ancient Seed", List.of(500, 500, 500, 500), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    STRAWBERRY_SEEDS("Strawberry Seeds", List.of(100, 100, 100, 100), 5, List.of(Season.SPRING), 0),
    COFFEE_BEANS("Coffee Beans", List.of(200, 200, 200, 200), 1, List.of(Season.SPRING, Season.SUMMER), 0),
    CARROT_SEEDS("Carrot Seeds", List.of(5, 5, 5, 5), 10, List.of(Season.SPRING), 0),
    RHUBARB_SEEDS("Rhubarb Seeds", List.of(100, 100, 100, 100), 5, List.of(Season.SPRING), 0),
    EGGPLANT_SEEDS("Eggplant Seeds", List.of(25, 25, 25, 25), 5, List.of(Season.FALL), 0),
    PUMPKIN_SEEDS("Pumpkin Seeds", List.of(125, 125, 125, 125), 5, List.of(Season.FALL), 0),
    BROCCOLI_SEEDS("Broccoli Seeds", List.of(15, 15, 15, 15), 5, List.of(Season.FALL), 0),
    AMARANTH_SEEDS("Amaranth Seeds", List.of(87, 87, 87, 87), 5, List.of(Season.FALL), 0),
    GRAPE_STARTER("Grape Starter", List.of(75, 75, 75, 75), 5, List.of(Season.FALL), 0),
    BEET_SEEDS("Beet Seeds", List.of(20, 20, 20, 20), 5, List.of(Season.FALL), 0),
    YAM_SEEDS("Yam Seeds", List.of(75, 75, 75, 75), 5, List.of(Season.FALL), 0),
    BOK_CHOYS_SEEDS("Bok Choy Seeds", List.of(62, 62, 62, 62), 5, List.of(Season.FALL), 0),
    CRANBERRY_SEEDS("Cranberry Seeds", List.of(300, 300, 300, 300), 5, List.of(Season.FALL), 0),
    FAIRY_SEEDS("Fairy Seeds", List.of(250, 250, 250, 250), 5, List.of(Season.FALL), 0),
    RARE_SEED("Rare Seed", List.of(1000, 1000, 1000, 1000), 1, List.of(Season.FALL), 0),
    POWDERMELON_SEEDS("Powdermelon Seeds", List.of(20, 20, 20, 20), 10, List.of(Season.WINTER), 0),
    BEER("Beer", List.of(400, 400, 400, 400), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    SALAD("Salad", List.of(220, 220, 220, 220), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    BREAD("Bread", List.of(120, 120, 120, 120), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    SPAGHETTI("Spaghetti", List.of(240, 240, 240, 240), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    PIZZA("Pizza", List.of(600, 600, 600, 600), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    COFFEE("Coffee", List.of(300, 300, 300, 300), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    HASHBROWNS_RECIPE("Hashbrowns Recipe", List.of(50, 50, 50, 50), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    OMELET_RECIPE("Omelet Recipe", List.of(100, 100, 100, 100), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    PANCAKES_RECIPE("Pancakes Recipe", List.of(100, 100, 100, 100), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    BREAD_RECIPE("Bread Recipe", List.of(100, 100, 100, 100), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    TORTILLA_RECIPE("Tortilla Recipe", List.of(100, 100, 100, 100), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    PIZZA_RECIPE("Pizza Recipe", List.of(150, 150, 150, 150), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    MAKI_ROLL_RECIPE("Maki Roll Recipe", List.of(300, 300, 300, 300), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    TRIPLE_SHOT_ESPRESSO_RECIPE("Triple Shot Espresso Recipe", List.of(5000, 5000, 5000, 5000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    COOKIE_RECIPE("Cookie Recipe", List.of(300, 300, 300, 300), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    CHICKEN("Chicken", List.of(800, 800, 800, 800), 2, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    COW("Cow", List.of(1500, 1500, 1500, 1500), 2, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    GOAT("Goat", List.of(4000, 4000, 4000, 4000), 2, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    DUCK("Duck", List.of(1200, 1200, 1200, 1200), 2, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    SHEEP("Sheep", List.of(8000, 8000, 8000, 8000), 2, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    RABBIT("Rabbit", List.of(8000, 8000, 8000, 8000), 2, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    DINOSAUR("Dinosaur", List.of(14000, 14000, 14000, 14000), 2, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    PIG("Pig", List.of(16000, 16000, 16000, 16000), 2, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    HAY("Hay", List.of(50, 50, 50, 50), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    MILK_PAIL("Milk Pail", List.of(1000, 1000, 1000, 1000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    SHEARS("Shears", List.of(1000, 1000, 1000, 1000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    COPPER_TOOL("Copper Tool", List.of(2000, 2000, 2000, 2000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    STEEL_TOOL("Steel Tool", List.of(5000, 5000, 5000, 5000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    GOLD_TOOL("Gold Tool", List.of(10000, 10000, 10000, 10000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    IRIDIUM_TOOL("Iridium Tool", List.of(25000, 25000, 25000, 25000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    COPPER_TRASH_CAN("Copper Trash Can", List.of(1000, 1000, 1000, 1000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    STEEL_TRASH_CAN("Steel Trash Can", List.of(2500, 2500, 2500, 2500), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    GOLD_TRASH_CAN("Gold Trash Can", List.of(5000, 5000, 5000, 5000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    IRIDIUM_TRASH_CAN("Iridium Trash Can", List.of(12500, 12500, 12500, 12500), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    COPPER_ORE("Copper Ore", List.of(75, 75, 75, 75), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    IRON_ORE("Iron Ore", List.of(150, 150, 150, 150), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    COAL("Coal", List.of(150, 150, 150, 150), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    GOLD_ORE("Gold Ore", List.of(400, 400, 400, 400), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),

    BARN("Barn", List.of(6000, 6000, 6000, 6000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    BIG_BARN("Big Barn", List.of(12000, 12000, 12000, 12000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    DELUXE_BARN("Deluxe Barn", List.of(25000, 25000, 25000, 25000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    COOP("Coop", List.of(4000, 4000, 4000, 4000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    BIG_COOP("Big Coop", List.of(10000, 10000, 10000, 10000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    DELUXE_COOP("Deluxe Coop", List.of(20000, 20000, 20000, 20000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    WELL("Well", List.of(1000, 1000, 1000, 1000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    SHIPPING_BIN("Shipping Bin", List.of(250, 250, 250, 250), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    WOOD("Wood", List.of(10, 10, 10, 10), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    STONE("Stone", List.of(20, 20, 20, 20), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0);





    private final String name;
    private final List<Integer> pricePerSeason;
    private final int dailyLimit;
    private final List<Season> availableSeasons;
    private final int requiredFishingLevel;

    ShopItems(String name, List<Integer> pricePerSeason, int dailyLimit,
              List<Season> availableSeasons, int requiredFishingLevel) {
        this.name = name;
        this.pricePerSeason = pricePerSeason;
        this.dailyLimit = dailyLimit;
        this.availableSeasons = availableSeasons;
        this.requiredFishingLevel = requiredFishingLevel;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getPricePerSeason() {
        return pricePerSeason;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public List<Season> getAvailableSeasons() {
        return availableSeasons;
    }

    public int getRequiredFishingLevel() {
        return requiredFishingLevel;
    }
    public static ShopItems getShopItem(String name){
        for (ShopItems item : ShopItems.values()) {
            if(item.getName().equalsIgnoreCase(name)){
                return item;
            }
        }
        return null;
    }
}
