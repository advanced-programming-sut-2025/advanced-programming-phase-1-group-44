package model.enums;

import model.Eatable;
import model.Food;
import model.Item;

public enum AllItems {
    //TODO fix prizes
    //TODO add all items
    Cotton("cotton", 1),
    Pumpkin_Pie("pumpkin pie", 1, true),
    Pizza("pizza", 1, true),
    Iron("Iron", 1),
    Stone("stone", 1),
    Diamond("diamond" , 1),
    Quartz("quartz" , 1),
    Gold_Bar("gold bar" , 1),
    Pumpkin("pumpkin" , 1, true),
    Wheat("wheat", 1, true),
    Iron_Ore("Iron ore", 1),
    Coffee("Coffee" , 1, true),
    Automatic_sprinkler("Automatic sprinkler", 1),
    Pickle("Pickle" , 1, true),
    Wine("Wine" , 1, true),
    Salmon("salmon" , 1, true),
    Grape("grape" , 1, true),
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
    Egg("egg", 1, true),
    Sardine("sardine", 1, true),
    fried_egg("fried egg", 35, true),
    baked_fish("baked fish", 100, true),
    salad("salad", 110, true),
    omelet("omelet", 125, true),
    pumpkin_pie("pumpkin pie", 385, true),
    spaghetti("spaghetti", 120, true),
    pizza("pizza", 300, true),
    tortilla("tortilla", 50, true),
    maki_roll("maki roll", 220, true),
    triple_shot_espresso("triple shot espresso", 450, true),
    cookie("cookie", 140, true),
    hash_browns("hash browns", 120, true),
    pancakes("pancakes", 80, true),
    fruit_salad("fruit salad", 450, true),
    red_plate("red plate", 400, true),
    bread("bread", 60, true),
    salmon_dinner("salmon dinner", 300, true),
    vegetable_medley("vegetable medley", 120, true),
    farmers_lunch("farmer's lunch", 150, true),
    survival_burger("survival burger", 180, true),
    dish_o_the_sea("dish o' the sea", 220, true),
    seafoam_pudding("seaform pudding", 300, true),
    miners_treat("miner's treat", 200, true),
    ;
    private String name;
    private int price;
    boolean isEatable;
    AllItems(String name, int x){
        this.name = name;
        this.price = x;
        this.isEatable = false;
    }
    AllItems(String name, int x , boolean flg){
        this.name = name;
        this.price = x;
        this.isEatable = flg;
    }
    public Item getItemByType(){
        if(isEatable){
            Food food = new Food(this.name, this.price);
            return food;
        }
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
