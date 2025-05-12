package model.enums.StoreItems;

import model.enums.Season;

import java.util.List;

public enum MarineRanchItems {
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
    SHEARS("Shears", List.of(1000, 1000, 1000, 1000), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0);


    private final String name;
    private final List<Integer> pricePerSeason;
    private final int dailyLimit;
    private final List<Season> availableSeasons;
    private final int requiredFishingLevel;

    MarineRanchItems(String name, List<Integer> pricePerSeason, int dailyLimit,
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
