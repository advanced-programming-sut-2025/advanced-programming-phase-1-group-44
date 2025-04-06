package model.enums;

import java.util.ArrayList;

public enum ToolType {
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
    ToolType(int maxLevel) {
        this.maxLevel = maxLevel;
    }
}
