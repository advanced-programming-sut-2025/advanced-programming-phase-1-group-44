package model.NPC;

import model.Item;
import model.Player;

public class TradeQuest extends Quest{
    Item reqItem , rewardItem;
    int reqCnt, rewardCnt, rewardMoney, rewardFriendship;

    public TradeQuest(Item reqItem, int reqCnt, Item rewardItem, int rewardCnt, int rewardMoney, int rewardFriendship) {
        this.reqItem = reqItem;
        this.rewardItem = rewardItem;
        this.reqCnt = reqCnt;
        this.rewardCnt = rewardCnt;
        this.rewardMoney = rewardMoney;
        this.rewardFriendship = rewardFriendship;
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
    public boolean canDoQuest(Player player) {
        return (player.getBackpack().contain(reqItem) >= reqCnt);
    }

    @Override
    public void doQuest(Player player , NPC npc) {
        player.getBackpack().removeItem(reqItem , reqCnt);
        if(!isDone)
            giveReward(player, npc);
        isDone = true;
    }
}

