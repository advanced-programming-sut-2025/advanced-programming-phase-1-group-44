package model.Stores;

import model.Item;
import model.enums.Season;
import model.enums.StoreItems.ShopItems;

import java.util.List;

public class ShopItem extends Item {
    private int dailyLimit;

    public ShopItem(String name, int price, int dailyLimit) {
        super(name , price);
        this.dailyLimit = dailyLimit;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }
    public void decreaseDailyLimit(int x){
        this.dailyLimit -= x;
    }
    public int getFishingLevel(){
        return ShopItems.getShopItem(this.name).getRequiredFishingLevel();
    }

    @Override
    public String toString() {
        String res = this.name + " : " + '\n';
        res += "price : " + this.price + '\n';
        res += "daily remaining: " + this.dailyLimit + '\n';
        return res;
    }
}
