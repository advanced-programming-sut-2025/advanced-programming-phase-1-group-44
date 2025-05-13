package model.Stores;

import java.util.ArrayList;

import model.enums.Material;
import model.enums.ShopEnum;

public class Shop {
    private ArrayList<Material> items = new ArrayList<>();

    public void sell() {

    }
    public ArrayList<Material> getItems() {
        return items;
    }
}
