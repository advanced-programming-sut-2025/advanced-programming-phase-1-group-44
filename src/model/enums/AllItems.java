package model.enums;

import model.Item;

public enum AllItems {
    //TODO fix prizes
    //TODO add all items
    Cotton("cotton", 1),
    Pumpkin_Pie("pumpkin pie", 1),
    Pizza("pizza", 1),
    Iron("Iron", 1),
    Stone("stonr", 1),
    Diamond("diamond" , 1),
    Quartz("quartz" , 1),
    Gold_Bar("gold bar" , 1),
    Pumpkin("pumpkin" , 1),
    Wheat("wheat", 1),
    Iron_Ore("Iron ore", 1),
    Coffee("Coffee" , 1),
    Automatic_sprinkler("Automatic sprinkler", 1),
    Pickle("Pickle" , 1),
    Wine("Wine" , 1),
    Salmon("salmon" , 1),
    Salad("salad", 1),
    ;
    String name;
    int price;
    AllItems(String name, int x){
        this.name = name;
        this.price = x;
    }
    public Item getItemByType(){
        Item item = new Item(this.name , this.price);
        return item;
    }
}
