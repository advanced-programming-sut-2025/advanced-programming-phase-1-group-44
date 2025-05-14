package model.NPC;

import model.Item;

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
}
