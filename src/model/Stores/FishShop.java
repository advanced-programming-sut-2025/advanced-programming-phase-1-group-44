package model.Stores;

import model.enums.ShopEnum;
import model.enums.StoreItems.FishShopItems;

import java.util.ArrayList;

public class FishShop extends Shop{
    ArrayList<FishShopItem> items;
    public FishShop(ShopEnum shopType) {
        super(shopType);
        for (FishShopItems value : FishShopItems.values()) {
            items.add(new FishShopItem(value));
        }
    }

}
