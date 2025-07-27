package model.enums.StoreItems;

import model.Stores.ShopItem;
import model.enums.Season;

import java.util.ArrayList;
import java.util.List;

public enum JojaMartItems implements ShopItemInterface{
    JOJA_COLA("Joja Cola", List.of(75, 75, 75, 75), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    ANCIENT_SEED("Ancient Seed", List.of(500, 500, 500, 500), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    GRASS_STARTER("Grass Starter", List.of(125, 125, 125, 125), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    SUGAR("Sugar", List.of(125, 125, 125, 125), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    WHEAT_FLOUR("Wheat Flour", List.of(125, 125, 125, 125), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    RICE("Rice", List.of(250, 250, 250, 250), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    PARSNIP_SEEDS("Parsnip Seeds", List.of(25, 25, 25, 25), 5, List.of(Season.SPRING), 0),
    BEAN_STARTER("Bean Starter", List.of(75, 75, 75, 75), 5, List.of(Season.SPRING), 0),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", List.of(100, 100, 100, 100), 5, List.of(Season.SPRING), 0),
    POTATO_SEEDS("Potato Seeds", List.of(62, 62, 62, 62), 5, List.of(Season.SPRING), 0),
    STRAWBERRY_SEEDS("Strawberry Seeds", List.of(100, 100, 100, 100), 5, List.of(Season.SPRING), 0),
    TULIP_BULB("Tulip Bulb", List.of(25, 25, 25, 25), 5, List.of(Season.SPRING), 0),
    KALE_SEEDS("Kale Seeds", List.of(87, 87, 87, 87), 5, List.of(Season.SPRING), 0),
    COFFEE_BEANS("Coffee Beans", List.of(200, 200, 200, 200), 1, List.of(Season.SPRING, Season.SUMMER), 0),
    CARROT_SEEDS("Carrot Seeds", List.of(5, 5, 5, 5), 10, List.of(Season.SPRING), 0),
    RHUBARB_SEEDS("Rhubarb Seeds", List.of(100, 100, 100, 100), 5, List.of(Season.SPRING), 0),
    JAZZ_SEEDS("Jazz Seeds", List.of(37, 37, 37, 37), 5, List.of(Season.SPRING), 0),
    CORN_SEEDS("Corn Seeds", List.of(187, 187, 187, 187), 5, List.of(Season.SUMMER, Season.FALL), 0),
    EGGPLANT_SEEDS("Eggplant Seeds", List.of(25, 25, 25, 25), 5, List.of(Season.FALL), 0),
    PUMPKIN_SEEDS("Pumpkin Seeds", List.of(125, 125, 125, 125), 5, List.of(Season.FALL), 0),
    BROCCOLI_SEEDS("Broccoli Seeds", List.of(15, 15, 15, 15), 5, List.of(Season.FALL), 0),
    AMARANTH_SEEDS("Amaranth Seeds", List.of(87, 87, 87, 87), 5, List.of(Season.FALL), 0),
    GRAPE_STARTER("Grape Starter", List.of(75, 75, 75, 75), 5, List.of(Season.FALL), 0),
    BEET_SEEDS("Beet Seeds", List.of(20, 20, 20, 20), 5, List.of(Season.FALL), 0),
    YAM_SEEDS("Yam Seeds", List.of(75, 75, 75, 75), 5, List.of(Season.FALL), 0),
    BOK_CHOYS_SEEDS("Bok Choy Seeds", List.of(62, 62, 62, 62), 5, List.of(Season.FALL), 0),
    CRANBERRY_SEEDS("Cranberry Seeds", List.of(300, 300, 300, 300), 5, List.of(Season.FALL), 0),
    SUNFLOWER_SEEDS("Sunflower Seeds", List.of(125, 125, 125, 125), 5, List.of(Season.SUMMER, Season.FALL), 0),
    FAIRY_SEEDS("Fairy Seeds", List.of(250, 250, 250, 250), 5, List.of(Season.FALL), 0),
    RARE_SEED("Rare Seed", List.of(1000, 1000, 1000, 1000), 1, List.of(Season.FALL), 0),
    WHEAT_SEEDS("Wheat Seeds", List.of(12, 12, 12, 12), 5, List.of(Season.SUMMER, Season.FALL), 0),
    POWDERMELON_SEEDS("Powdermelon Seeds", List.of(20, 20, 20, 20), 10, List.of(Season.WINTER), 0);

    private final String name;
    private final List<Integer> pricePerSeason;
    private final int dailyLimit;
    private final List<Season> availableSeasons;
    private final int requiredFishingLevel;

    JojaMartItems(String name, List<Integer> pricePerSeason, int dailyLimit,
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
    public static ArrayList<ShopItem> getItems(int seasonID) {
        ArrayList<ShopItem> items = new ArrayList<>();
        for (JojaMartItems value : JojaMartItems.values()) {
            items.add(new ShopItem(value.getName(), value.getPricePerSeason().get(seasonID), value.getDailyLimit()));
        }
        return items;
    }
}
