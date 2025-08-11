package model.Abilities;

import model.App;
import model.Player;
import model.enums.CraftingItems.CraftableItem;
import model.enums.Recipe;

public class Farming extends ability{
    public Farming() {
        this.level = 0;
        this.xp = 0;
    }
    @Override
    public void levelUp() {
        int prevLevel = this.level;
        super.levelUp();
        int nowLevel = this.level;
        if(prevLevel == nowLevel)
            return;
        Player player = App.getCurrentGame().getCurrentPlayer();
        if(this.level == 1){
            player.addCraftableItem(CraftableItem.SPRINKLER);
            player.addCraftableItem(CraftableItem.BEE_HOUSE);
            player.addRecipe(Recipe.FARMERS_LUNCH);
        }
        if(this.level == 2){
            player.addCraftableItem(CraftableItem.QUALITY_SPRINKLER);
            player.addCraftableItem(CraftableItem.DELUXE_SCARECROW);
            player.addCraftableItem(CraftableItem.CHEESE_PRESS);
            player.addCraftableItem(CraftableItem.PRESERVES_JAR);
        }
        if(this.level == 3){
            player.addCraftableItem(CraftableItem.IRIDIUM_SPRINKLER);
            player.addCraftableItem(CraftableItem.KEG);
            player.addCraftableItem(CraftableItem.LOOM);
            player.addCraftableItem(CraftableItem.OIL_MAKER);
        }
    }
}
