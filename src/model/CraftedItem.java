package model;

import model.enums.CraftingItems.CraftableItem;

public class CraftedItem extends Item{
    CraftableItem craftableItem;

    public CraftedItem(String name) {
        super(name, CraftableItem.getCraftableItem(name).getSellPrice());
        this.name = name;
        this.craftableItem = CraftableItem.getCraftableItem(name);
        this.price = this.craftableItem.getSellPrice();
    }

    public CraftedItem(CraftableItem craftableItem) {
        super(craftableItem.getName(), craftableItem.getSellPrice());
        this.craftableItem = craftableItem;
        this.name = this.craftableItem.getName();
        this.price = this.craftableItem.getSellPrice();
    }
}
