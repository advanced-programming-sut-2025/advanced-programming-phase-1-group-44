package model.NPC;

import model.App;
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
    public void giveReward(Player player, NPC npc) {
        player.addRecipe(recipe);
    }

    @Override
    public void doQuest(Player player, NPC npc) {
        player.getBackpack().removeItem(reqItem, reqCnt);
        if(!this.isDone) {
            giveReward(player, npc);
        }
        this.isDone = true;
    }

    @Override
    public boolean checkBackpack(Player player) {
        return true;
    }

    @Override
    public String toString() {
        String res = "you give : " + reqCnt + " ta " + reqItem.name + '\n';
        res += App.getCurrentGame().getQuestOwner(this).name + " give : " + recipe.getName();
        return res;
    }
}
