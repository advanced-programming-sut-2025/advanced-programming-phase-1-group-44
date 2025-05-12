package model.enums.StoreItems;

import model.Stores.ShopItem;
import model.enums.Season;

import java.util.ArrayList;
import java.util.List;

public enum StardropSaloonItems implements ShopItemInterface{
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
    COOKIE_RECIPE("Cookie Recipe", List.of(300, 300, 300, 300), 1, List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER), 0);

    private final String name;
    private final List<Integer> pricePerSeason;
    private final int dailyLimit;
    private final List<Season> availableSeasons;
    private final int requiredFishingLevel;

    StardropSaloonItems(String name, List<Integer> pricePerSeason, int dailyLimit,
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
        for (StardropSaloonItems value : StardropSaloonItems.values()) {
            items.add(new ShopItem(value.getName(), value.getPricePerSeason().get(seasonID), value.getDailyLimit()));
        }
        return items;
    }
}
