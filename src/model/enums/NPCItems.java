package model.enums;

public enum NPCItems {
    //TODO  fix prices;
    //TODO fix names;
    Cotton("cotton", 1),
    Pumpkin_Pie("pumpkin pie", 1),
    Pizza("pizza", 1),
    Iron("Iron", 1),
    Stone("stonr", 1),
    Diamond("diamond" , 1),
    Quartz("quartz" , 1),
    Gold("gold" , 1),
    Pumpkin("pumpkin" , 1),
    Wheat("wheat", 1),

    ;
    String name;
    int price;

    NPCItems(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
