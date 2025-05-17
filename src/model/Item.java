package model;

import model.enums.Material;

public class Item extends MapObj{
    public int price;
    public String name;
    Material type;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
        this.type = Material.normal;
    }

    public Item(int price, String name, Material type) {
        this.price = price;
        this.name = name;
        this.type = type;
    }

    public void setType(Material type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
