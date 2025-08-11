package model.Abilities;

import model.App;
import model.Player;
import model.enums.CraftingItems.CraftableItem;
import model.enums.Recipe;

public class Fishing extends ability{
    public Fishing() {
        this.level = 0;
        this.xp = 0;
    }
    public void levelUp() {
        int prevLevel = this.level;
        super.levelUp();
        int nowLevel = this.level;
        if(prevLevel == nowLevel)
            return;
        Player player = App.getCurrentGame().getCurrentPlayer();
        if(this.level == 2){
            player.addRecipe(Recipe.DISH_O_THE_SEA);
        }
        if(this.level == 3){
            player.addRecipe(Recipe.SEAFOAM_PUDDING);
        }
    }
}
