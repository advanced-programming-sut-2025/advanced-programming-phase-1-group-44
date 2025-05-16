package model.NPC;

import model.Item;
import model.Player;

public class SamePlantQuest extends Quest{
    Item rewardItem;
    int reqCnt, rewardCnt, rewardMoney, rewardFriendship;

    public SamePlantQuest(int reqCnt, Item rewardItem, int rewardCnt, int rewardMoney, int rewardFriendship) {
        this.reqCnt = reqCnt;
        this.rewardItem = rewardItem;
        this.rewardCnt = rewardCnt;
        this.rewardMoney = rewardMoney;
        this.rewardFriendship = rewardFriendship;
    }

    @Override
    public boolean canDoQuest(Player player) {
        Item maxPlant = player.getBackpack().getMaxPlant();
        if (maxPlant == null){
            return false;
        }
        return (player.getBackpack().contain(maxPlant) >= reqCnt);
    }

    @Override
    public void giveReward(Player player, NPC npc) {
        player.getBackpack().putItem(rewardItem, rewardCnt);
        player.money += rewardMoney;
        player.addNpcFriendShip(npc.getName(), rewardFriendship);
        if (player.getNpcFriendship(npc.getName()) >= 400){
            player.getBackpack().putItem(rewardItem, rewardCnt);
            player.money += rewardMoney;
            player.addNpcFriendShip(npc.getName(), rewardFriendship);
        }
    }

    @Override
    public void doQuest(Player player, NPC npc) {
        Item maxPlant = player.getBackpack().getMaxPlant();
        player.getBackpack().removeItem(maxPlant, reqCnt);
        if(!isDone)
            giveReward(player, npc);
        isDone = true;
    }
}
