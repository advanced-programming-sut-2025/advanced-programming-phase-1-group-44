package model.enums.StoreItems;

import model.Stores.ShopItem;
import model.enums.Season;

import java.util.ArrayList;
import java.util.List;

public enum BlackSmithItems implements ShopItemInterface{
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
    GOLD_ORE("Gold Ore", List.of(400, 400, 400, 400), Integer.MAX_VALUE, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0);



    private final String name;
    private final List<Integer> pricePerSeason;
    private final int dailyLimit;
    private final List<Season> availableSeasons;
    private final int requiredFishingLevel;

    BlackSmithItems(String name, List<Integer> pricePerSeason, int dailyLimit,
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
        for (BlackSmithItems value : BlackSmithItems.values()) {
            items.add(new ShopItem(value.getName(), value.getPricePerSeason().get(seasonID), value.getDailyLimit()));
        }
        return items;
    }
}
