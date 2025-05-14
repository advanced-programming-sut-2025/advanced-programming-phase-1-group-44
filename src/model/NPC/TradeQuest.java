package model.NPC;

import model.Item;

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
}
