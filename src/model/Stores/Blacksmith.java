package model.Stores;

import model.enums.ShopEnum;
import model.enums.StoreItems.BlackSmithItems;

import java.util.ArrayList;

public class Blacksmith extends Shop{
    public Blacksmith(ShopEnum shopType) {
        super(shopType);
        for (BlackSmithItems value : BlackSmithItems.values()) {
            items.add(new ShopItem(value.getDisplayName(), value.getPrice(), value.getDailyLimit()));
        }
    }

}
