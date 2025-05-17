package model.enums;

import model.Item;

public enum AllItems {
    //TODO fix prizes
    //TODO add all items
    Cotton("cotton", 1),
    Pumpkin_Pie("pumpkin pie", 1),
    Pizza("pizza", 1),
    Iron("Iron", 1),
    Stone("stone", 1),
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
    Grape("grape" , 1),
    Wood("wood" , 1),
    Iron_Bar("Iron Bar", 1),
    Spaghetti("spaghetti", 1),
    Bee_House("bee house" , 1),
    cherry_bomb("cherry bomb", 50),
    bomb("bomb", 50),
    mega_bomb("mega bomb", 50),
    sprinkler("sprinkler", 0),
    quality_sprinkler("quality sprinkler", 0),
    iridium_sprinkler("iridium sprinkler", 0),
    charcoal_kiln("charcoal kiln", 0),
    furnace("furnace", 0),
    scarecrow("scarecrow", 0),
    deluxe_scarecrow("deluxe scarecrow", 0),
    bee_house("bee house", 0),
    cheese_press("cheese press", 0),
    keg("keg", 0),
    loom("loom", 0),
    mayonnaise_machine("mayonnaise machine", 0),
    oil_maker("oil maker", 0),
    preserves_jar("preserves jar", 0),
    dehydrator("dehydrator", 0),
    grass_starter("grass starter", 0),
    fish_smoker("fish smoker", 0),
    mystic_tree_seed("mystic tree seed", 100),
    copper_bar("copper bar", 1),
    ;
    private final String name;
    private final int price;
    AllItems(String name, int x){
        this.name = name;
        this.price = x;
    }
    public Item getItemByType(){
        Item item = new Item(this.name , this.price);
        return item;
    }
    public static Item getItemByName(String name){
        for (AllItems value : AllItems.values()) {
            if(value.name.equals(name)){
                return value.getItemByType();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
