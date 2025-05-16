package model.NPC;

import model.Player;

public class Quest {
    public boolean canDoQuest(Player player){
        return true;
    }
    public void doQuest(Player player){

    }
    public boolean checkBackpack(Player player){
        return !player.getBackpack().isFull();
    }
}
