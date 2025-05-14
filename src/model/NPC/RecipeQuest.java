package model.NPC;

import model.Item;
import model.enums.Recipe;

public class RecipeQuest extends Quest{
    Item reqItem;
    int reqCnt;
    Recipe recipe;

    public RecipeQuest(Item reqItem, int reqCnt, Recipe recipe) {
        this.reqItem = reqItem;
        this.reqCnt = reqCnt;
        this.recipe = recipe;
    }
}
