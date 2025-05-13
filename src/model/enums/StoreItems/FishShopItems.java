package model.enums.StoreItems;

import model.Stores.ShopItem;
import model.enums.Season;

import java.util.ArrayList;
import java.util.List;

public enum FishShopItems implements ShopItemInterface{
    FISH_SMOKER_RECIPE("Fish Smoker (Recipe)", List.of(10000, 10000, 10000, 10000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    TROUT_SOUP("Trout Soup", List.of(250, 250, 250, 250), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    BAMBOO_POLE("Bamboo Pole", List.of(500, 500, 500, 500), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    TRAINING_ROD("Training Rod", List.of(25, 25, 25, 25), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0),
    FIBERGLASS_ROD("Fiberglass Rod", List.of(1800, 1800, 1800, 1800), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 2),
    IRIDIUM_ROD("Iridium Rod", List.of(7500, 7500, 7500, 7500), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 4);

    private final String name;
    private final List<Integer> pricePerSeason;
    private final int dailyLimit;
    private final List<Season> availableSeasons;
    private final int requiredFishingLevel;

    FishShopItems(String name, List<Integer> pricePerSeason, int dailyLimit,
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
        for (FishShopItems value : FishShopItems.values()) {
            items.add(new ShopItem(value.getName(), value.getPricePerSeason().get(seasonID), value.getDailyLimit()));
        }
        return items;
    }
}
