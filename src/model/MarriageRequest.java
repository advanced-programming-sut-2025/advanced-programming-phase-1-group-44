package model;

public class MarriageRequest {
    private Player sender;
    private Player receiver;
    private Item ring;

    MarriageRequest(Player sender, Player receiver, Item ring) {
        this.sender = sender;
        this.receiver = receiver;
        this.ring = ring;
    }
    public Player getReceiver() {
        return receiver;
    }
    public Item getRing() {
        return ring;
    }
    public Player getSender() {
        return sender;
    }
}
