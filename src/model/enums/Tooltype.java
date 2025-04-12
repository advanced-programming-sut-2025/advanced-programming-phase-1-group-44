package model.enums;

public enum Tooltype {
    hoe(4),
    pickaxe(4),
    axe(4),
    wateringCan(4),
    fishingPole(3),
    seythe(0),
    milkPail(0),
    shear(0),
    backpack(2),
    Trashcan(4),
    ;
    int maxLevel;
    //level is zero base
    Tooltype(int maxLevel) {
        this.maxLevel = maxLevel;
    }
}
