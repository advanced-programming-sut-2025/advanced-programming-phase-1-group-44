package model;

public class Trade {
    private int ID;
    private Player sender, receiver;
    private String type;
    private Item reqItem, targerItem;
    private int reqCnt, targetMoney, targetCnt;

    public Trade(int ID, Player sender, Player receiver, String type, Item reqItem, Item targerItem, int reqCnt, int targetMoney, int targetCnt) {
        this.ID = ID;
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.reqItem = reqItem;
        this.targerItem = targerItem;
        this.reqCnt = reqCnt;
        this.targetMoney = targetMoney;
        this.targetCnt = targetCnt;
    }

    @Override
    public String toString() {
        String res = "trade " + this.ID + '\n';
        res += "sender : " + this.sender.getName() + '\n' + "receiver : " + this.receiver.getName() + '\n';
        res += "type : " + this.type + '\n' + "reqItem : " + reqItem.name + '\n';
        res += "reqCnt : " + this.reqCnt + '\n';
        if(targetCnt == 0){
            res += "target Money : " + this.targetMoney;
        }
        else{
            res += "target item : " + this.targerItem.toString() + '\n' + "target cnt : " + this.targetCnt;
        }
        return res;
    }

    public int getID() {
        return ID;
    }

    public Player getSender() {
        return sender;
    }

    public Player getReceiver() {
        return receiver;
    }

    public String getType() {
        return type;
    }

    public Item getReqItem() {
        return reqItem;
    }

    public Item getTargerItem() {
        return targerItem;
    }

    public int getReqCnt() {
        return reqCnt;
    }

    public int getTargetMoney() {
        return targetMoney;
    }

    public int getTargetCnt() {
        return targetCnt;
    }
}
