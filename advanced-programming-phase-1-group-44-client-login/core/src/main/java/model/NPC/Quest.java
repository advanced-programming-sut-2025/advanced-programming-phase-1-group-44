package model.NPC;

import model.Player;

public class Quest {
    public boolean isDone = false;
    public boolean canDoQuest(Player player){
        return true;
    }
    public void giveReward(Player player, NPC npc){

    }
    public void doQuest(Player player, NPC npc){
        if(!isDone)
            giveReward(player , npc);
        isDone = true;
    }
    public boolean checkBackpack(Player player){
        return !player.getBackpack().isFull();
    }
}
