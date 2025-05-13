package model.Stores;

import model.Item;
import model.enums.Season;
import model.enums.StoreItems.ShopItems;

import java.util.List;

public class ShopItem extends Item {
    private int dailyLimit;

    public ShopItem(String name, int price, int dailyLimit) {
        this.dailyLimit = dailyLimit;
        this.name = name;
        this.price = price;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }
}
