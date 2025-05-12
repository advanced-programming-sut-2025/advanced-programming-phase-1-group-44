package model.enums.StoreItems;

import model.enums.Season;

import java.util.List;

public enum CarpenterItems {
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

    CarpenterItems(String name, List<Integer> pricePerSeason, int dailyLimit,
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
}
