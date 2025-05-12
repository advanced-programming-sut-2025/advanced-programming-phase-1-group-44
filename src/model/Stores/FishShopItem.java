package model.Stores;

import model.Item;
import model.enums.StoreItems.FishShopItems;

public class FishShopItem extends ShopItem {
    private FishShopItems type;

    public FishShopItem(FishShopItems type) {
        super(type.getDisplayName(), type.getPrice() , type.getDailyLimit());
        this.type = type;
    }

}
