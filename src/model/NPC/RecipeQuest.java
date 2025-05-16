package model.NPC;

import model.Item;
import model.Player;
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

    @Override
    public boolean canDoQuest(Player player) {
        return  (player.getBackpack().contain(reqItem) >= reqCnt);
    }

    @Override
    public void doQuest(Player player) {
        player.getBackpack().removeItem(reqItem, reqCnt);
        player.addRecipe(recipe);
    }

    @Override
    public boolean checkBackpack(Player player) {
        return true;
    }
}
