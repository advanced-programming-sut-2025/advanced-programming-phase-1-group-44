package model.enums.StoreItems;

import model.enums.Season;

import java.util.Arrays;
import java.util.List;

public enum PierreStoreItems {
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
    RED_CABBAGE_SEEDS("Red Cabbage Seeds", List.of(100, 150, 150, 150), 5, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0);


    private final String name;
    private final List<Integer> pricePerSeason;
    private final int dailyLimit;
    private final List<Season> availableSeasons;
    private final int requiredFishingLevel;

    PierreStoreItems(String name, List<Integer> pricePerSeason, int dailyLimit,
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
