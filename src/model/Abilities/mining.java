package model.Abilities;

import model.App;
import model.Player;
import model.enums.CraftingItems.CraftableItem;

public class mining extends ability{
    public mining() {
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
            player.addCraftableItem(CraftableItem.CHERRY_BOMB);
        }
        if(this.level == 2){
            player.addCraftableItem(CraftableItem.BOMB);
        }
        if(this.level == 3){
            player.addCraftableItem(CraftableItem.MEGA_BOMB);
        }
    }
}
