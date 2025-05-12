package model.Stores;

import model.enums.ShopEnum;
import model.enums.StoreItems.StardropSaloonItems;

public class StardropSaloon extends Shop{

    public StardropSaloon(ShopEnum shopType) {
        super(shopType);
        for (StardropSaloonItems value : StardropSaloonItems.values()) {
            items.add(new ShopItem(value.getDisplayName(), value.getPrice(), value.getDailyLimit()));
        }
    }
}
