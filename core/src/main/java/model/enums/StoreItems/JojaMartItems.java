package model.enums.StoreItems;

import model.Stores.ShopItem;
import model.enums.Season;

import java.util.ArrayList;
import java.util.List;

public enum JojaMartItems implements ShopItemInterface{
    JOJA_COLA("Joja Cola", List.of(75, 75, 75, 75), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0, "ShopItems/Joja_Cola_%28large%29.png"),
    ANCIENT_SEED("Ancient Seed", List.of(500, 500, 500, 500), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0,"ShopItems/Ancient_Seed.png"),
    GRASS_STARTER("Grass Starter", List.of(125, 125, 125, 125), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0, "ShopItems/Grass_Starter.png"),
    SUGAR("Sugar", List.of(125, 125, 125, 125), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0, "ShopItems/Sugar.png"),
    WHEAT_FLOUR("Wheat Flour", List.of(125, 125, 125, 125), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0, "ShopItems/Wheat_Flour.png"),
    RICE("Rice", List.of(250, 250, 250, 250), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0, "ShopItems/Rice.png"),
    PARSNIP_SEEDS("Parsnip Seeds", List.of(25, 25, 25, 25), 5, List.of(Season.SPRING), 0, "ShopItems/Parsnip_Seeds.png"),
    BEAN_STARTER("Bean Starter", List.of(75, 75, 75, 75), 5, List.of(Season.SPRING), 0, "ShopItems/Bean_Starter.png"),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", List.of(100, 100, 100, 100), 5, List.of(Season.SPRING), 0, "ShopItems/Cauliflower_Seeds.png"),
    POTATO_SEEDS("Potato Seeds", List.of(62, 62, 62, 62), 5, List.of(Season.SPRING), 0, "ShopItems/Potato_Seeds.png"),
    STRAWBERRY_SEEDS("Strawberry Seeds", List.of(100, 100, 100, 100), 5, List.of(Season.SPRING), 0, "ShopItems/Strawberry_Seeds.png"),
    TULIP_BULB("Tulip Bulb", List.of(25, 25, 25, 25), 5, List.of(Season.SPRING), 0, "ShopItems/Tulip_Bulb.png"),
    KALE_SEEDS("Kale Seeds", List.of(87, 87, 87, 87), 5, List.of(Season.SPRING), 0, "ShopItems/Kale_Seeds.png"),
    COFFEE_BEANS("Coffee Beans", List.of(200, 200, 200, 200), 1, List.of(Season.SPRING, Season.SUMMER), 0, "ShopItems/Coffee_Bean.png"),
    CARROT_SEEDS("Carrot Seeds", List.of(5, 5, 5, 5), 10, List.of(Season.SPRING), 0, "ShopItems/Carrot_Seeds.png"),
    RHUBARB_SEEDS("Rhubarb Seeds", List.of(100, 100, 100, 100), 5, List.of(Season.SPRING), 0, "ShopItems/Rhubarb_Seeds.png"),
    JAZZ_SEEDS("Jazz Seeds", List.of(37, 37, 37, 37), 5, List.of(Season.SPRING), 0, "ShopItems/Jazz_Seeds.png"),
    CORN_SEEDS("Corn Seeds", List.of(187, 187, 187, 187), 5, List.of(Season.SUMMER, Season.FALL), 0, "ShopItems/Corn_Seeds.png"),
    EGGPLANT_SEEDS("Eggplant Seeds", List.of(25, 25, 25, 25), 5, List.of(Season.FALL), 0, "ShopItems/Eggplant_Seeds.png"),
    PUMPKIN_SEEDS("Pumpkin Seeds", List.of(125, 125, 125, 125), 5, List.of(Season.FALL), 0, "ShopItems/Pumpkin_Seeds.png"),
    BROCCOLI_SEEDS("Broccoli Seeds", List.of(15, 15, 15, 15), 5, List.of(Season.FALL), 0, "ShopItems/Broccoli_Seeds.png"),
    AMARANTH_SEEDS("Amaranth Seeds", List.of(87, 87, 87, 87), 5, List.of(Season.FALL), 0, "ShopItems/Amaranth_Seeds.png"),
    GRAPE_STARTER("Grape Starter", List.of(75, 75, 75, 75), 5, List.of(Season.FALL), 0, "ShopItems/Grape_Starter.png"),
    BEET_SEEDS("Beet Seeds", List.of(20, 20, 20, 20), 5, List.of(Season.FALL), 0, "ShopItems/Beet_Seeds.png"),
    YAM_SEEDS("Yam Seeds", List.of(75, 75, 75, 75), 5, List.of(Season.FALL), 0, "ShopItems/Yam_Seeds.png"),
    BOK_CHOYS_SEEDS("Bok Choy Seeds", List.of(62, 62, 62, 62), 5, List.of(Season.FALL), 0, "ShopItems/Bok_Choy_Seeds.png"),
    CRANBERRY_SEEDS("Cranberry Seeds", List.of(300, 300, 300, 300), 5, List.of(Season.FALL), 0, "ShopItems/Cranberry_Seeds.png"),
    SUNFLOWER_SEEDS("Sunflower Seeds", List.of(125, 125, 125, 125), 5, List.of(Season.SUMMER, Season.FALL), 0,"ShopItems/Sunflower_Seeds.png"),
    FAIRY_SEEDS("Fairy Seeds", List.of(250, 250, 250, 250), 5, List.of(Season.FALL), 0, "ShopItems/Fairy_Seeds.png"),
    RARE_SEED("Rare Seed", List.of(1000, 1000, 1000, 1000), 1, List.of(Season.FALL), 0, "ShopItems/Rare_Seed.png"),
    WHEAT_SEEDS("Wheat Seeds", List.of(12, 12, 12, 12), 5, List.of(Season.SUMMER, Season.FALL), 0, "ShopItems/Wheat_Seeds.png"),
    POWDERMELON_SEEDS("Powdermelon Seeds", List.of(20, 20, 20, 20), 10, List.of(Season.WINTER), 0, "ShopItems/Powdermelon_Seeds.png");

    private final String name;
    private final List<Integer> pricePerSeason;
    private final int dailyLimit;
    private final List<Season> availableSeasons;
    private final int requiredFishingLevel;
    private final String imagePath;

    JojaMartItems(String name, List<Integer> pricePerSeason, int dailyLimit,
              List<Season> availableSeasons, int requiredFishingLevel, String imagePath) {
        this.name = name;
        this.pricePerSeason = pricePerSeason;
        this.dailyLimit = dailyLimit;
        this.availableSeasons = availableSeasons;
        this.requiredFishingLevel = requiredFishingLevel;
        this.imagePath = imagePath;
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
            items.add(new ShopItem(value.getName(), value.getPricePerSeason().get(seasonID), value.getDailyLimit(), value.imagePath));
        }
        return items;
    }
}
