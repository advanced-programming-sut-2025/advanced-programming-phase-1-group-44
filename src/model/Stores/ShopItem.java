package model.Stores;

import model.Item;

public class ShopItem extends Item {
    private int dailyLimit;
    public ShopItem(String name, int price, int dailyLimit) {
        this.name = name;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    @Override
    public String toString() {
        String output = "name: " + this.name + " price: " + this.price;
        return output;
    }
}
