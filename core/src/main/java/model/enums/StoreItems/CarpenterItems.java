package model.enums.StoreItems;

import model.Stores.ShopItem;
import model.enums.Season;

import java.util.ArrayList;
import java.util.List;

public enum CarpenterItems implements ShopItemInterface{
    BARN("Barn", List.of(6000, 6000, 6000, 6000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0, "ShopItems/Wood.png"),
    BIG_BARN("Big Barn", List.of(12000, 12000, 12000, 12000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0, "ShopItems/Wood.png"),
    DELUXE_BARN("Deluxe Barn", List.of(25000, 25000, 25000, 25000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0, "ShopItems/Wood.png"),
    COOP("Coop", List.of(4000, 4000, 4000, 4000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0, "ShopItems/Wood.png"),
    BIG_COOP("Big Coop", List.of(10000, 10000, 10000, 10000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0, "ShopItems/Wood.png"),
    DELUXE_COOP("Deluxe Coop", List.of(20000, 20000, 20000, 20000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0, "ShopItems/Wood.png"),
    WELL("Well", List.of(1000, 1000, 1000, 1000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0, "ShopItems/Wood.png"),
    SHIPPING_BIN("Shipping Bin", List.of(250, 250, 250, 250), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0, "ShopItems/Wood.png"),
    WOOD("Wood", List.of(10, 10, 10, 10), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0, "ShopItems/Wood.png"),
    STONE("Stone", List.of(20, 20, 20, 20), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0, "ShopItems/Stone.png");

    private final String name;
    private final List<Integer> pricePerSeason;
    private final int dailyLimit;
    private final List<Season> availableSeasons;
    private final int requiredFishingLevel;
    private final String imagePath;

    CarpenterItems(String name, List<Integer> pricePerSeason, int dailyLimit,
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
        for (CarpenterItems value : CarpenterItems.values()) {
            items.add(new ShopItem(value.getName(), value.getPricePerSeason().get(seasonID), value.getDailyLimit(), value.imagePath));
        }
        return items;
    }
}
