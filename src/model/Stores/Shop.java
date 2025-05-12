package model.Stores;

import java.util.ArrayList;
import java.util.Map;

import model.enums.Material;
import model.enums.ShopEnum;

public class Shop {
    private ShopEnum shopType;
    ArrayList<ShopItem> items;

    public Shop(ShopEnum shopType) {
        this.shopType = shopType;
    }
    public boolean contain(String name){
        boolean flg = false;
        for (ShopItem item : items) {
            if(item.name.equalsIgnoreCase(name)){
                flg = true;
            }
        }
        return flg;
    }

    public ShopEnum getShopType() {
        return shopType;
    }

    public ArrayList<ShopItem> getItems() {
        return items;
    }
}
