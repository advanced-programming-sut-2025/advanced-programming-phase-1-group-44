package model.Stores;

import java.util.ArrayList;
import java.util.Map;

import model.Item;
import model.enums.Material;
import model.enums.ShopEnum;
import model.enums.StoreItems.ShopItemInterface;
import model.enums.StoreItems.ShopItems;

public class Shop {
    private ShopEnum shopType;
    private ArrayList<ShopItem> items = new ArrayList<>();
    public Shop(ShopEnum shopType, ArrayList<ShopItem> items) {
        this.shopType = shopType;
        this.items = items;
    }
    public String getName(){
        return this.shopType.name();
    }
    public ArrayList<ShopItem> getItems(){
        return items;
    }
    public ShopItem getItem(String name){
        for (ShopItem item : items) {
            if(item.name.equals(name)){
                return item;
            }
        }
        return null;
    }
}
